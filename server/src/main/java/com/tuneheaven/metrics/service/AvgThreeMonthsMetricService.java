package com.tuneheaven.metrics.service;

import com.tuneheaven.metrics.api.MonthlyAverageSongMetricsResponse;
import com.tuneheaven.metrics.domain.DateRange;
import com.tuneheaven.metrics.domain.DateService;
import com.tuneheaven.metrics.domain.SongArithmeticAverageScoreService;
import com.tuneheaven.metrics.interfaces.error.RestApiFailure;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AvgThreeMonthsMetricService {

    private static final Logger LOG = LoggerFactory.getLogger(AvgThreeMonthsMetricService.class);

    private final SongArithmeticAverageScoreService songArithmeticAverageScoreService;
    private final DateService dateService;

    public AvgThreeMonthsMetricService(SongArithmeticAverageScoreService songArithmeticAverageScoreService,
                                       DateService dateService) {
        this.songArithmeticAverageScoreService = songArithmeticAverageScoreService;
        this.dateService = dateService;
    }

    public ResponseEntity<List<MonthlyAverageSongMetricsResponse>> getAvgThreeMonthMetrics(final String songId) {
        List<DateRange> previousThreeFullMonthsDateRanges = dateService.getPreviousThreeFullMonthsDateRanges();
        var firstMonthRange = previousThreeFullMonthsDateRanges.get(0);
        var secondMothRange = previousThreeFullMonthsDateRanges.get(1);
        var thirdMonthRage = previousThreeFullMonthsDateRanges.get(2);

        var firstMonthResult = getArithmeticAverageForSongScore(songId, previousThreeFullMonthsDateRanges.get(0));
        var secondMonthResult = getArithmeticAverageForSongScore(songId, previousThreeFullMonthsDateRanges.get(1));
        var thirdMonthResult = getArithmeticAverageForSongScore(songId, previousThreeFullMonthsDateRanges.get(2));

        if (firstMonthResult.isRight() && secondMonthResult.isRight() && thirdMonthResult.isRight()) {
            return ResponseEntity.ok(List.of(
                    new MonthlyAverageSongMetricsResponse(getYearMonth(firstMonthRange), firstMonthResult.get()),
                    new MonthlyAverageSongMetricsResponse(getYearMonth(secondMothRange), secondMonthResult.get()),
                    new MonthlyAverageSongMetricsResponse(getYearMonth(thirdMonthRage), thirdMonthResult.get())
            ));
        }

        LOG.warn("Calculation of three months arithmetic average failed.");
        return ResponseEntity.status(500).build();
    }

    private Either<RestApiFailure, Double> getArithmeticAverageForSongScore(String songId, DateRange dateRange) {
        return songArithmeticAverageScoreService.getArithmeticAverageForSongScore(songId, dateRange.getStartDate(), dateRange.getEndDate());
    }

    private String getYearMonth(DateRange dateRange) {
        return dateRange.getStartDate().format(DateTimeFormatter.ofPattern("yyyyMM"));
    }
}

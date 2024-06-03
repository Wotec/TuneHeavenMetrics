package com.tuneheaven.metrics.service;

import com.tuneheaven.metrics.domain.SongArithmeticAverageScoreService;
import com.tuneheaven.metrics.interfaces.error.RestApiFailure;
import io.vavr.Tuple2;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SongMetricsService {

    private static final Logger LOG = LoggerFactory.getLogger(SongMetricsService.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final SongArithmeticAverageScoreService songArithmeticAverageScoreService;

    public SongMetricsService(SongArithmeticAverageScoreService songArithmeticAverageScoreService) {
        this.songArithmeticAverageScoreService = songArithmeticAverageScoreService;
    }

    public ResponseEntity<String> getMetrics(final String songId, final String dateSince, final String dateUntil) {
        return validateDateRange(dateSince, dateUntil)
                .flatMap(datesRange -> songArithmeticAverageScoreService.getArithmeticAverageForSongScore(songId, datesRange._1, datesRange._2))
                .fold(
                        left -> ResponseEntity.status(left.getCode()).body(left.getMessage()),
                        right -> ResponseEntity.ok(right.toString())
                );
    }

    private Either<RestApiFailure, Tuple2<LocalDate, LocalDate>> validateDateRange(String dateSince, String dateUntil) {
        var dateSinceValidationResult = validateDate(dateSince);
        var dateUntilValidationResult = validateDate(dateUntil);

        return dateSinceValidationResult.flatMap(startDate ->
                dateUntilValidationResult.flatMap(endDate -> performDateRangeValidation(startDate, endDate)));
    }

    private Either<RestApiFailure, Tuple2<LocalDate, LocalDate>> performDateRangeValidation(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            return Either.left(new RestApiFailure(403, "Invalid date range"));
        }
        return Either.right(new Tuple2<>(startDate, endDate));
    }

    private Either<RestApiFailure, LocalDate> validateDate(String dateString) {
        try {
            var date = LocalDate.parse(dateString, DATE_TIME_FORMATTER);
            return Either.right(date);
        } catch (DateTimeParseException e) {
            LOG.warn("Invalid date format for: {}", dateString);
            return Either.left(new RestApiFailure(403, "Invalid date format."));
        }
    }

}

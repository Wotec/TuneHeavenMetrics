package com.tuneheaven.metrics.api;

import com.tuneheaven.metrics.client.ApiUrls;
import com.tuneheaven.metrics.service.AvgThreeMonthsMetricService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AvgThreeMonthSongMetricsEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(AvgThreeMonthSongMetricsEndpoint.class);

    private final AvgThreeMonthsMetricService avgThreeMonthsMetricService;

    public AvgThreeMonthSongMetricsEndpoint(AvgThreeMonthsMetricService avgThreeMonthsMetricService) {
        this.avgThreeMonthsMetricService = avgThreeMonthsMetricService;
    }

    @GetMapping(ApiUrls.GET_MONTHLY_AVERAGE_SONG_METRICS_URL)
    public ResponseEntity<List<MonthlyAverageSongMetricsResponse>> getMonthlyAverageSongMetrics(@PathVariable("songId") String songId) {
        LOG.info("Retrieved request for avg three months metrics with params: songId={}",
                songId);
        return avgThreeMonthsMetricService.getAvgThreeMonthMetrics(songId);
    }

}

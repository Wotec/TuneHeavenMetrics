package com.tuneheaven.metrics.api;

import com.tuneheaven.metrics.client.ApiUrls;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonthlySongMetricsEndpoint {

    @GetMapping(ApiUrls.GET_MONTHLY_AVERAGE_SONG_METRICS_URL)
    public ResponseEntity<GetMonthlyAverageSongMetricsResponse> getMonthlyAverageSongMetrics(@PathVariable("songId") String songId) {
        return null;
    }

}

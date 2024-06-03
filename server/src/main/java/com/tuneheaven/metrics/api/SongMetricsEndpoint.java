package com.tuneheaven.metrics.api;

import com.tuneheaven.metrics.client.ApiUrls;
import com.tuneheaven.metrics.service.SongMetricService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongMetricsEndpoint {
    private static final Logger LOG = LoggerFactory.getLogger(SongMetricsEndpoint.class);

    private final SongMetricService songMetricService;

    public SongMetricsEndpoint(SongMetricService songMetricService) {
        this.songMetricService = songMetricService;
    }

    @GetMapping(ApiUrls.GET_AVERAGE_SONG_METRICS_URL)
    public ResponseEntity<String> getAverageSongMetrics(@PathVariable("songId") String songId,
                                                        @RequestParam("since") String dateSince,
                                                        @RequestParam("until") String dateUntil) {
        LOG.info("Retrieved request for song metrics with params: songId={}, dateSince={}, dateUnit={}",
                songId, dateSince, dateUntil);
        return songMetricService.getMetrics(songId, dateSince, dateUntil);
    }

}

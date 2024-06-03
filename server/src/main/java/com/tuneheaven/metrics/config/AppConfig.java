package com.tuneheaven.metrics.config;

import com.tuneheaven.metrics.domain.DateService;
import com.tuneheaven.metrics.domain.SongArithmeticAverageScoreService;
import com.tuneheaven.metrics.interfaces.SongMetricsRepository;
import com.tuneheaven.metrics.service.AvgThreeMonthsMetricService;
import com.tuneheaven.metrics.service.SongMetricService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class AppConfig {

    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    SongArithmeticAverageScoreService songArithmeticAverageScoreService(SongMetricsRepository songMetricsRepository) {
        return new SongArithmeticAverageScoreService(songMetricsRepository);
    }

    @Bean
    AvgThreeMonthsMetricService avgThreeMonthsMetricService(SongArithmeticAverageScoreService songArithmeticAverageScoreService,
                                                            DateService dateService) {
        return new AvgThreeMonthsMetricService(songArithmeticAverageScoreService, dateService);
    }

    @Bean
    SongMetricService songMetricsService(SongArithmeticAverageScoreService songArithmeticAverageScoreService) {
        return new SongMetricService(songArithmeticAverageScoreService);
    }

    @Bean
    DateService dateService(Clock clock) {
        return new DateService(clock);
    }

}

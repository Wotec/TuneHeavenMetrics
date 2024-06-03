package com.tuneheaven.metrics.repository;

import com.tuneheaven.metrics.interfaces.SongMetricsRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfig {

    @Bean
    SongMetricsRepository songMetricsRepository(NamedParameterJdbcTemplate metricsNamedParameterJdbcTemplate) {
        return new SongMetricsSqlRepository(metricsNamedParameterJdbcTemplate);
    }


    @Bean
    public NamedParameterJdbcTemplate metricsNamedParameterJdbcTemplate(DataSource metricsDataSource) {
        return new NamedParameterJdbcTemplate(metricsDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "metrics.datasource")
    public DataSource metricsDataSource() {
        return DataSourceBuilder.create().build();
    }

}

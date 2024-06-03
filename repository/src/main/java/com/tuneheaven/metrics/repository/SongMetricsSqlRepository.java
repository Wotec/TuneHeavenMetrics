package com.tuneheaven.repository;

import com.tuneheaven.interfaces.SongMetricsRepository;
import com.tuneheaven.interfaces.error.RepositoryError;
import com.tuneheaven.interfaces.model.SongMetric;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDate;

public class SongMetricsSqlRepository implements SongMetricsRepository {

    private static final Logger LOG = LoggerFactory.getLogger(SongMetricsSqlRepository.class);

    private static final String SAVE_SONG_ENTRY_QUERY = "";
    private static final String GET_SCORE_SUM_FOR_SONG_QUERY = "";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SongMetricsSqlRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Either<RepositoryError, Integer> save(SongMetric metric) {
        return null;
    }

    @Override
    public Either<RepositoryError, Integer> getScoreSumForSong(String songId, LocalDate dateSince, LocalDate dateUntil) {
        return null;
    }

    @Override
    public Either<RepositoryError, Integer> getEntriesCountForSong(String songId, LocalDate dateSince, LocalDate dateUntil) {
        return null;
    }
}

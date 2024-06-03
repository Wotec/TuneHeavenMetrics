package com.tuneheaven.metrics.repository;

import com.tuneheaven.metrics.interfaces.SongMetricsRepository;
import com.tuneheaven.metrics.interfaces.error.RepositoryError;
import com.tuneheaven.metrics.interfaces.model.SongMetric;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class SongMetricsSqlRepository implements SongMetricsRepository {

    private static final String SAVE_SONG_ENTRY_QUERY = "INSERT INTO songs (import_date, song_id, song_name, artist_id, artist_name, user_id, rating, genre) " +
            "VALUES (:importDate, :songId, :songName, :artistId, :artistName, :userId, :rating, :genre)";
    private static final String GET_SCORE_SUM_FOR_SONG_QUERY = "SELECT AVG(rating) FROM songs WHERE song_id = :songId AND import_date BETWEEN :startDate AND :endDate";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SongMetricsSqlRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Either<RepositoryError, Integer> save(final SongMetric metric) {
        try {
            var parameterSource = new MapSqlParameterSource()
                    .addValue("importDate", LocalDate.now())
                    .addValue("songId", metric.getSongId())
                    .addValue("songName", metric.getSongName())
                    .addValue("artistId", metric.getAuthorId())
                    .addValue("artistName", metric.getAuthorName())
                    .addValue("userId", metric.getUserId())
                    .addValue("rating", metric.getScore())
                    .addValue("genre", metric.getGenre());

            var keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(SAVE_SONG_ENTRY_QUERY, parameterSource, keyHolder, new String[]{"id"});
            return Either.right(keyHolder.getKey().intValue());
        } catch (DataAccessException e) {
            return Either.left(new RepositoryError(e.getMessage()));
        }
    }

    @Override
    public Either<RepositoryError, Double> getArithmeticAverageForSong(final String songId,
                                                                       final LocalDate dateSince,
                                                                       final LocalDate dateUntil) {
        try {
            var parameters = new MapSqlParameterSource()
                    .addValue("songId", songId)
                    .addValue("startDate", dateSince)
                    .addValue("endDate", dateUntil);
            var queryResult = namedParameterJdbcTemplate.queryForObject(GET_SCORE_SUM_FOR_SONG_QUERY, parameters, Double.class);
            var scoreSum = (Objects.nonNull(queryResult) && queryResult > 0.0) ? queryResult : 0.0;
            return Either.right(scoreSum);
        } catch (Exception e) {
            return Either.left(new RepositoryError(e.getMessage()));
        }
    }

    @Override
    public Either<RepositoryError, Integer> getEntriesCountForSong(String songId, LocalDate dateSince, LocalDate dateUntil) {
        return null;
    }
}

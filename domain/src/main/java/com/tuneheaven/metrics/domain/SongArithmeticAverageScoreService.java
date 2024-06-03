package com.tuneheaven.metrics.domain;

import com.tuneheaven.metrics.interfaces.SongMetricsRepository;
import com.tuneheaven.metrics.interfaces.error.RepositoryError;
import com.tuneheaven.metrics.interfaces.error.RestApiFailure;
import io.vavr.control.Either;

import java.time.LocalDate;

public class SongArithmeticAverageScoreService {

    private final SongMetricsRepository songMetricsRepository;

    public SongArithmeticAverageScoreService(SongMetricsRepository songMetricsRepository) {
        this.songMetricsRepository = songMetricsRepository;
    }

    public Either<RestApiFailure, Double> getArithmeticAverageForSongScore(final String songId,
                                                                            final LocalDate dateSince,
                                                                            final LocalDate dateUntil) {
        return songMetricsRepository.getArithmeticAverageForSong(songId, dateSince, dateUntil)
                .mapLeft(this::mapRepositoryError);
    }

    private RestApiFailure mapRepositoryError(RepositoryError repositoryError) {
        return new RestApiFailure(503, repositoryError.getMessage());
    }

}

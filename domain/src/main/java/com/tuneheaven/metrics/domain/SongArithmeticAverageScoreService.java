package com.tuneheaven.domain;

import com.tuneheaven.interfaces.SongMetricsRepository;
import com.tuneheaven.interfaces.error.RepositoryError;
import com.tuneheaven.interfaces.error.RestApiFailure;
import io.vavr.control.Either;

import java.time.LocalDate;

public class SongArithmeticAverageScoreService {

    private final SongMetricsRepository songMetricsRepository;

    public SongArithmeticAverageScoreService(SongMetricsRepository songMetricsRepository) {
        this.songMetricsRepository = songMetricsRepository;
    }

    public Either<RestApiFailure, Integer> getArithmeticAverageForSongScore(final String songId,
                                                                           final LocalDate dateSince,
                                                                           final LocalDate dateUntil) {
        return songMetricsRepository.getEntriesCountForSong(songId, dateSince, dateUntil)
                .flatMap(entriesCount -> calculateArithmeticAverage(entriesCount, songId, dateSince, dateUntil))
                .mapLeft(this::mapRepositoryError);
    }

    private Either<RepositoryError, Integer> calculateArithmeticAverage(Integer count, String songId,
                                                                        LocalDate dateSince, LocalDate dateUntil) {
        return songMetricsRepository.getScoreSumForSong(songId, dateSince, dateUntil)
                .map(scoreSum -> scoreSum / count);
    }

    private RestApiFailure mapRepositoryError(RepositoryError repositoryError) {
        return new RestApiFailure(500, "test");
    }

}

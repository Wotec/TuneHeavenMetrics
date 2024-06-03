package com.tuneheaven.interfaces;

import com.tuneheaven.interfaces.error.RepositoryError;
import com.tuneheaven.interfaces.model.SongMetric;
import io.vavr.control.Either;

import java.time.LocalDate;

public interface SongMetricsRepository {

    Either<RepositoryError, Integer> save(SongMetric metric);

    Either<RepositoryError, Integer> getScoreSumForSong(String songId, LocalDate dateSince, LocalDate dateUntil);

    Either<RepositoryError, Integer> getEntriesCountForSong(String songId, LocalDate dateSince, LocalDate dateUntil);

}

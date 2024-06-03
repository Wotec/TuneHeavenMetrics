package com.tuneheaven.metrics.interfaces;

import com.tuneheaven.metrics.interfaces.error.RepositoryError;
import com.tuneheaven.metrics.interfaces.model.SongMetric;
import io.vavr.control.Either;

import java.time.LocalDate;

public interface SongMetricsRepository {

    Either<RepositoryError, Integer> save(SongMetric metric);

    Either<RepositoryError, Double> getArithmeticAverageForSong(String songId, LocalDate dateSince, LocalDate dateUntil);

    Either<RepositoryError, Integer> getEntriesCountForSong(String songId, LocalDate dateSince, LocalDate dateUntil);

}

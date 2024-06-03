package com.tuneheaven.metrics.interfaces;

import io.vavr.control.Either;

import java.nio.file.Path;

public interface SongMetricsFileLocationProvider {

    Either<Error, Path> getFilePath(String fileUrl);

}

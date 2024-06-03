package com.tuneheaven.metrics.job;

import com.tuneheaven.metrics.interfaces.SongMetricsRepository;
import com.tuneheaven.metrics.interfaces.model.SongMetric;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class SongStatisticsImportJob {

    private static final Logger LOG = LoggerFactory.getLogger(SongStatisticsImportJob.class);

    private static final String FILE_NAME = "/tune-heaven-songs-%s.csv";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final SongMetricsRepository songMetricsRepository;

    @Value("${song.statistic.file.path}")
    private String path;

    public SongStatisticsImportJob(SongMetricsRepository songMetricsRepository) {
        this.songMetricsRepository = songMetricsRepository;
    }

    @Scheduled(cron = "${cron.song.statistic.file.import}")
    public void runSongStatisticImport() {
        var filePath = String.format(FILE_NAME, LocalDate.now().format(DATE_TIME_FORMATTER));
        try (Reader in = new FileReader(path + filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                var entry = new SongMetric(record.get(0), record.get(1), record.get(2),
                        record.get(3), record.get(4), Integer.valueOf(record.get(5)), record.get(6));
                songMetricsRepository.save(entry)
                        .peek(id -> LOG.info("Saved record={}", id))
                        .peekLeft(error -> LOG.warn("Failed to save statistics for: {} with error={}", record.toString(), error.getMessage()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.tuneheaven.metrics.api;


public class MonthlyAverageSongMetricsResponse {

    private final String month;
    private final Double avg;

    public MonthlyAverageSongMetricsResponse(String month, Double avg) {
        this.month = month;
        this.avg = avg;
    }

    public String getMonth() {
        return month;
    }

    public Double getAvg() {
        return avg;
    }
}

package com.tuneheaven.metrics.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;

public class TuneHeavenMetricsClient {

    private static final Logger LOG = LoggerFactory.getLogger(TuneHeavenMetricsClient.class);
    private static final String URL_CONTEXT = "/api";

    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final String url;

    public TuneHeavenMetricsClient(String path, int port) {
        this.url = new StringBuilder()
                .append("http://")
                .append(path)
                .append(":")
                .append(port)
                .append(URL_CONTEXT)
                .toString();
    }

    public


}

package com.tuneheaven.metrics.test;

import com.sun.tools.javac.Main;
import com.tuneheaven.metrics.client.TuneHeavenMetricsClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Main.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class BaseTest {

    @LocalServerPort
    protected int port;

    protected TuneHeavenMetricsClient client;

    @BeforeEach
    void setUp() {
        client = new TuneHeavenMetricsClient("localhost", port);
    }
}

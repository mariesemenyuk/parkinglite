package com.epamtraining.parking;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.Optional;

public class PostgresContainer {
    public static final PostgreSQLContainer POSTGRE_CONTAINER =
            new PostgreSQLContainer("postgres:latest");

    static {
        Startables.deepStart(Collections.singletonList(POSTGRE_CONTAINER)).join();
    }

    public static void properties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.username", POSTGRE_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRE_CONTAINER::getPassword);
        registry.add("spring.datasource.url", POSTGRE_CONTAINER::getJdbcUrl);
    }

    @BeforeAll
    static void containers() {
        Assertions.assertThat(POSTGRE_CONTAINER.isRunning()).isTrue();
    }

    @AfterAll
    static void tearDown() {
        POSTGRE_CONTAINER.stop();
    }
}

package io.github.hrashk.contacts.web.app;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class PostgreSQLInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final DockerImageName IMAGE = DockerImageName.parse("postgres:16-alpine");
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>(IMAGE)
            .withUsername("postgres")
            .withPassword("postgres")
            .withDatabaseName("contacts")
            .withInitScript("init.sql")
            .waitingFor(Wait.forListeningPort());

    @Override
    public void initialize(final ConfigurableApplicationContext applicationContext) {
        POSTGRE_SQL_CONTAINER.start();
        TestPropertyValues.of(
                "spring.datasource.url=%s&currentSchema=contacts_schema".formatted(POSTGRE_SQL_CONTAINER.getJdbcUrl()),
                "spring.datasource.username=" + POSTGRE_SQL_CONTAINER.getUsername(),
                "spring.datasource.password=" + POSTGRE_SQL_CONTAINER.getPassword()
        ).applyTo(applicationContext.getEnvironment());
    }
}

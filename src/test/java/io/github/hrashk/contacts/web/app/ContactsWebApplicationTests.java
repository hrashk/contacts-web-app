package io.github.hrashk.contacts.web.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = PostgreSQLInitializer.class)
class ContactsWebApplicationTests {

    @Test
    void contextLoads() {
    }

}

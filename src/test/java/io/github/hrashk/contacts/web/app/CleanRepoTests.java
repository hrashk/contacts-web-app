package io.github.hrashk.contacts.web.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest(properties = "app.contacts.generate=false")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ContactsService.class, ContactsRepository.class, ContactsInitializer.class})
public class CleanRepoTests {
    @Autowired
    private ApplicationContext context;

    @Test
    void initializerIsNotInjected() {
        assertThat(context.getBeansOfType(ContactsInitializer.class)).isEmpty();
    }
}

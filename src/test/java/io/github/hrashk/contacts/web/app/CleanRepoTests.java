package io.github.hrashk.contacts.web.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@ContainerJdbcTest
@Import({ContactsService.class, ContactsRepository.class, ContactsInitializer.class})
public class CleanRepoTests {
    @Autowired
    private ApplicationContext context;

    @Test
    void initializerIsNotInjected() {
        assertThat(context.getBeansOfType(ContactsInitializer.class)).isEmpty();
    }
}

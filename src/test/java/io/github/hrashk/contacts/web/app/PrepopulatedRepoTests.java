package io.github.hrashk.contacts.web.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@ContainerJdbcTest
@TestPropertySource(properties = "app.contacts.generate=true")
@Import({ContactsService.class, ContactsRepository.class, ContactsInitializer.class})
public class PrepopulatedRepoTests {
    @Autowired
    private ContactsService service;

    @Test
    void thereAreSomeContactsInitially() {
        assertThat(service.findAllContacts()).hasSizeGreaterThan(5);
    }
}

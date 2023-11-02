package io.github.hrashk.contacts.web.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest(properties = "app.contacts.generate=false")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ContactsService.class, ContactsRepository.class, ContactsGenerator.class})
public class CleanRepoTests {
    @Autowired
    private ContactsService service;

    @Test
    void thereAreNoContactsInitially() {
        var contacts = service.findAllContacts();
        assertThat(contacts).isEmpty();
    }
}

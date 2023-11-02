package io.github.hrashk.contacts.web.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("classpath:sample.sql")
@Import({ContactsService.class, ContactsRepository.class})
class ContactsServiceTest {
    @Autowired
    private ContactsService service;

    @Test
    void findAllContacts() {
        var contacts = service.findAllContacts();
        assertThat(contacts).hasSize(5);
    }
}

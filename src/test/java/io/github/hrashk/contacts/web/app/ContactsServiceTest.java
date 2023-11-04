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
        assertThat(contacts).hasSizeGreaterThan(4);
    }

    @Test
    void deleteContact() {
        var contacts = service.findAllContacts();
        Contact firstContact = contacts.iterator().next();

        service.deleteContact(firstContact.id());

        var newContacts = service.findAllContacts();
        assertThat(newContacts).hasSize(contacts.size() - 1);

        var remainingContactIds = newContacts.stream().map(Contact::id).toList();
        assertThat(remainingContactIds).doesNotContain(firstContact.id());
    }

    @Test
    void createContact() {
        int originalSize = service.findAllContacts().size();
        Contact contact = new Contact(777, "", "", "", "");

        service.createContact(contact);

        var newContacts = service.findAllContacts();
        assertThat(newContacts).hasSize(originalSize + 1);
        assertThat(newContacts).anyMatch(contact::isSimilarTo);
    }

    @Test
    void findById() {
        var contacts = service.findAllContacts();
        Contact firstContact = contacts.iterator().next();

        Contact found = service.findById(firstContact.id());

        assertThat(found).isEqualTo(firstContact);
    }

    @Test
    void editContact() {
        var contacts = service.findAllContacts();
        Contact firstContact = contacts.iterator().next();

        var updated = firstContact.toBuilder()
                .firstName("Alter")
                .lastName("Ego")
                .build();

        service.editContact(updated);

        var newContact = service.findById(firstContact.id());
        assertThat(newContact).isEqualTo(updated);
    }
}

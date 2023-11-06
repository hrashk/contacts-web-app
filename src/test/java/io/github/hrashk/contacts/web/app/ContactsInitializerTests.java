package io.github.hrashk.contacts.web.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Checking just the functionality here. The auto-injection behavior is tested elsewhere.
 * @see CleanRepoTests
 * @see PrepopulatedRepoTests
 */
@ContainerJdbcTest
@Import({ContactsService.class, ContactsRepository.class})
public class ContactsInitializerTests {
    @Autowired
    private ContactsService service;

    @Test
    void generatesSomeContacts() throws Exception {
        service.clearContacts();
        assertThat(service.findAllContacts()).isEmpty();

        new ContactsInitializer(service).run();

        assertThat(service.findAllContacts()).hasSizeGreaterThan(5);
    }

    @Test
    @Sql("classpath:sample.sql")
    void doesNotGenerateIfNotEmpty() throws Exception {
        int initialCount = service.findAllContacts().size();

        new ContactsInitializer(service).run();

        assertThat(service.findAllContacts()).hasSize(initialCount);
    }
}

package io.github.hrashk.contacts.web.app;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * we have to be careful not to inject an actual generator bean, because it would make permanent changes to the db.
 */
@JdbcTest(properties = "app.contacts.generate=true")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ContactsService.class, ContactsRepository.class})
public class ContactsGeneratorTests {
    @Autowired
    private ContactsService service;

    @MockBean
    private ContactsGenerator generator;

    @Test
    void generatorIsCalled() throws Exception {
        Mockito.verify(generator).run();
    }

    @Test
    void generatesSomeContacts() throws Exception {
        var localGen = new ContactsGenerator(service);
        localGen.run();

        var contacts = service.findAllContacts();
        assertThat(contacts).hasSizeGreaterThan(5);
    }

    @Test
    void doesNotGenerateIfNotEmpty() throws Exception {
        var localGen = new ContactsGenerator(service);

        localGen.run();
        int intitalCount = service.findAllContacts().size();

        localGen.run();

        var contacts = service.findAllContacts();
        assertThat(contacts).hasSize(intitalCount);
    }
}

package io.github.hrashk.contacts.web.app;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("app.contacts.generate")
@RequiredArgsConstructor
public class ContactsInitializer implements CommandLineRunner {
    private final ContactsService service;
    private final ContactsGenerator generator = new ContactsGenerator();

    @Override
    public void run(String... args) throws Exception {
        if (!service.findAllContacts().isEmpty())
            return;

        service.addAllContacts(generator.generate());
    }
}

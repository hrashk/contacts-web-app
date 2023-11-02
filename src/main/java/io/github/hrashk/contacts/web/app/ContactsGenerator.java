package io.github.hrashk.contacts.web.app;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Component
@ConditionalOnProperty("app.contacts.generate")
@RequiredArgsConstructor
public class ContactsGenerator implements CommandLineRunner {
    private final ContactsService service;

    @Override
    public void run(String... args) throws Exception {
        if (!service.findAllContacts().isEmpty())
            return;

        var random = ThreadLocalRandom.current();
        int numberOfContacts = random.nextInt(7, 13);
        Faker f = new Faker(random);

        List<Contact> contacts = IntStream.range(0, numberOfContacts)
                .mapToObj(idx -> aRandomContact(f))
                .toList();
        service.addAllContacts(contacts);
    }

    private static Contact aRandomContact(Faker f) {
        String firstName = f.name().firstName();
        String lastName = f.name().lastName();
        String email = String.format("%s.%s@%s",
                firstName.toLowerCase(),
                lastName.toLowerCase(),
                f.internet().domainName());
        return Contact.builder()
                .id(0)  // ignored
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(f.phoneNumber().phoneNumber())
                .build();
    }
}

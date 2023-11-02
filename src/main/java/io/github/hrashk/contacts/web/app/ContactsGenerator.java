package io.github.hrashk.contacts.web.app;

import lombok.RequiredArgsConstructor;
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
        var random = ThreadLocalRandom.current();

        List<Contact> contacts = IntStream.range(0, random.nextInt(7, 13))
                .mapToObj(idx -> aRandomContact())
                .toList();
        service.addAllContacts(contacts);
    }

    private static Contact aRandomContact() {
        return Contact.builder()
                .id(0)  // ignored
                .firstName("Uasya")
                .lastName("Pupkin")
                .email("a@b.com")
                .phone("+777712341234")
                .build();
    }
}

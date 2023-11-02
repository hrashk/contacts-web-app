package io.github.hrashk.contacts.web.app;

import net.datafaker.Faker;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ContactsGenerator {
    private final Random random = ThreadLocalRandom.current();
    private final Faker faker = new Faker(random);

    public List<Contact> generate() {
        int numberOfContacts = random.nextInt(7, 13);

        return generate(numberOfContacts);
    }

    public List<Contact> generate(int count) {
        return IntStream.range(0, count)
                .mapToObj(idx -> aRandomContact(idx + 1))
                .toList();
    }

    public Contact aRandomContact(int id) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String localPart = String.format("%s.%s",
                firstName.toLowerCase(),
                lastName.toLowerCase());
        String email = faker.internet().emailAddress(localPart);

        return Contact.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(faker.phoneNumber().phoneNumberInternational())
                .build();
    }
}

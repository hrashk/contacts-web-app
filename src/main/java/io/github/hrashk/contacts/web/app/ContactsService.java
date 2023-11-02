package io.github.hrashk.contacts.web.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsRepository repository;

    public Collection<Contact> findAllContacts() {
        return repository.findAll();
    }

    public void addAllContacts(Collection<Contact> contact) {
        repository.addAll(contact);
    }

    public void clearContacts() {
        repository.clear();
    }
}

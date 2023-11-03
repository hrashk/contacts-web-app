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

    public void deleteContact(int id) {
        repository.deleteById(id);
    }

    public void createContact(Contact contact) {
        repository.add(contact);
    }

    public Contact findById(int id) {
        return repository.findById(id);
    }

    public void editContact(Contact contact) {

    }
}

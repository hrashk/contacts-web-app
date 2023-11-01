package io.github.hrashk.contacts.web.app;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ContactsService {
    public Collection<Contact> findAllContacts() {
        return List.of();
    }
}

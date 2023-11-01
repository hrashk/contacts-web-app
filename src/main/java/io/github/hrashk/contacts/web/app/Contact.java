package io.github.hrashk.contacts.web.app;

import lombok.experimental.Accessors;

@lombok.Value
@Accessors(fluent = true)
public class Contact {
    int id;
    String firstName;
    String lastName;
    String email;
    String phone;
}

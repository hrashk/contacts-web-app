package io.github.hrashk.contacts.web.app;

import lombok.Builder;
import lombok.experimental.Accessors;

@lombok.Value
@Accessors(fluent = true)
@Builder
public class Contact {
    int id;
    String firstName;
    String lastName;
    String email;
    String phone;
}

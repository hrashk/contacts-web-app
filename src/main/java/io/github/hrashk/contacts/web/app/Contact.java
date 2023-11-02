package io.github.hrashk.contacts.web.app;

import lombok.Builder;

@Builder
public record Contact(int id, String firstName, String lastName, String email, String phone) {
}

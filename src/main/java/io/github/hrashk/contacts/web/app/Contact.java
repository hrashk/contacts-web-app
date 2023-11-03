package io.github.hrashk.contacts.web.app;

import lombok.Builder;

import java.util.Objects;

@Builder
public record Contact(int id, String firstName, String lastName, String email, String phone) {
    public boolean similarTo(Contact other) {
        return Objects.equals(firstName, other.firstName) &&
                Objects.equals(lastName, other.lastName) &&
                Objects.equals(email, other.email) &&
                Objects.equals(phone, other.phone);
    }
}

package io.github.hrashk.contacts.web.app;

import lombok.Builder;

import java.util.Objects;

@Builder(toBuilder = true)
public record Contact(int id, String firstName, String lastName, String email, String phone) {
    public boolean isSimilarTo(Contact other) {
        return other != null &&
                Objects.equals(firstName, other.firstName) &&
                Objects.equals(lastName, other.lastName) &&
                Objects.equals(email, other.email) &&
                Objects.equals(phone, other.phone);
    }
}

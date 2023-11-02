package io.github.hrashk.contacts.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ContactsRepository {
    @Autowired
    private JdbcTemplate jdbc;

    public Collection<Contact> findAll() {
        return jdbc.query("select * from contacts", (rs, rowNum) -> Contact.builder()
                .id(rs.getInt("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .build());
    }
}

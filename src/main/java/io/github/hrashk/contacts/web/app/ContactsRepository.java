package io.github.hrashk.contacts.web.app;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ContactsRepository {
    private static final String INSERT_SQL = "insert into contacts (first_name, last_name, email, phone) values (?, ?, ?, ?)";
    private final JdbcTemplate jdbc;

    public Collection<Contact> findAll() {
        return jdbc.query("select * from contacts", (rs, rowNum) -> Contact.builder()
                .id(rs.getInt("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .build());
    }

    public void addAll(Collection<Contact> contacts) {
        jdbc.batchUpdate(INSERT_SQL, contacts, contacts.size(), ContactsRepository::setInsertParams);
    }

    private static void setInsertParams(PreparedStatement ps, Contact c) throws SQLException {
        ps.setString(1, c.firstName());
        ps.setString(2, c.lastName());
        ps.setString(3, c.email());
        ps.setString(4, c.phone());
    }

    public void clear() {
        jdbc.execute("truncate table contacts");
    }

    public void deleteById(int id) {
        jdbc.update("delete from contacts where id = ?", id);
    }

    public void add(Contact contact) {
        jdbc.update(INSERT_SQL, ps -> setInsertParams(ps, contact));
    }
}

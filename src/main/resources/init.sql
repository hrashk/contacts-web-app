CREATE SCHEMA contacts_schema

    CREATE TABLE contacts
    (
        id         serial primary key,
        first_name varchar not null,
        last_name  varchar not null,
        email      varchar not null,
        phone      varchar not null
    );

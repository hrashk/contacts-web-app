CREATE SCHEMA conntacts_schema
    CREATE TABLE contacts (
        id int not null,
        first_name varchar not null,
        last_name varchar not null,
        email varchar not null,
        phone varchar not null
    );

--liquibase formatted sql

--changeset aroskach:1
CREATE INDEX students_name ON student (name);

--changeset aroskach:2
CREATE INDEX  faculties_name_color ON  faculty (name, color);

CREATE DATABASE litequizappdb;
CREATE USER postgres WITH password 'admin';
GRANT ALL privileges ON DATABASE litequizappdb TO postgres;

CREATE TABLE IF NOT EXISTS categories
(
    id     BIGSERIAL PRIMARY KEY NOT NULL,
    title  VARCHAR(100) NOT NULL
);
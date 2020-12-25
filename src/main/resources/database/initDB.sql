CREATE TABLE IF NOT EXISTS categories
(
    id     BIGSERIAL PRIMARY KEY NOT NULL,
    title  VARCHAR(100) NOT NULL
);

CREATE SEQUENCE hibernate_sequence START 1;
SELECT setval('hibernate_sequence', (SELECT max(id) FROM public.categories));
--liquibase formatted sql

--changeset rmerzlyakov:1
CREATE TABLE IF NOT EXISTS revision
(
    id        serial primary key,
    timestamp bigint not null
);

CREATE TABLE if not exists users_aud
(
    id         bigint,
    rev        int references revision (id),
    revtype    smallint,
    username   varchar(64) not null unique,
    birth_date date,
    firstname  varchar(64),
    lastname   varchar(64),
    role       varchar(32),
    company_id int
);


--liquibase formatted sql

--changeset rmerzlyakov:1
CREATE TABLE IF NOT EXISTS car
(
    id        uuid primary key ,
    brand varchar(128),
    model varchar(128),
    user_id bigint references users(id)
);
--rollback DROP TABLE car;



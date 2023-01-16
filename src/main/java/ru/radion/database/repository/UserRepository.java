package ru.radion.database.repository;

import ru.radion.database.connectionPool.ConnectionPool;

public class UserRepository {
    private final ConnectionPool connectionPool;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}

package ru.radion.database.repository;

import ru.radion.database.connectionPool.ConnectionPool;

public class CompanyRepository {
    private final ConnectionPool pool;

    public CompanyRepository(ConnectionPool pool) {
        this.pool = pool;
    }

    public static CompanyRepository of (ConnectionPool connectionPool) {
        return new CompanyRepository(connectionPool);
    }
}

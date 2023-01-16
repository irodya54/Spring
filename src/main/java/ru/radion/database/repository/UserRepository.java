package ru.radion.database.repository;

import org.springframework.stereotype.Repository;
import ru.radion.database.connectionPool.ConnectionPool;
@Repository
public class UserRepository {
    private final ConnectionPool pool1;

    public UserRepository(ConnectionPool pool1) {
        this.pool1 = pool1;
    }
}

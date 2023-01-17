package ru.radion.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.radion.database.connectionPool.ConnectionPool;
@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final ConnectionPool pool1;

}

package ru.radion.database.repository;

import ru.radion.annotacions.InjectBeant;
import ru.radion.database.connectionPool.ConnectionPool;

public class CompanyRepository {
    @InjectBeant
    private ConnectionPool pool;

}

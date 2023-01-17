package ru.radion.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.radion.TestMain;
import ru.radion.annotation.IT;
import ru.radion.config.DatabaseProperties;
import ru.radion.database.connectionPool.ConnectionPool;
import ru.radion.service.CompanyService;
import ru.radion.service.UserService;

@IT
public class UserServiceItTest extends TestMain {
    public UserServiceItTest(CompanyService companyService, DatabaseProperties databaseProperties, ConnectionPool pool1) {
        super(companyService, databaseProperties, pool1);
    }

    @Test
    void findById() {
        System.out.println();
    }
}

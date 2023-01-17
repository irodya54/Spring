package ru.radion;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;
import ru.radion.annotation.IT;
import ru.radion.config.DatabaseProperties;
import ru.radion.database.connectionPool.ConnectionPool;
import ru.radion.service.CompanyService;
@IT
@TestConfiguration
@RequiredArgsConstructor
public class TestMain {
    protected final CompanyService companyService;
    protected final DatabaseProperties databaseProperties;
    protected final ConnectionPool pool1;
}

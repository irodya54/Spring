package ru.radion.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.radion.TestMain;
import ru.radion.annotation.IT;
import ru.radion.config.DatabaseProperties;
import ru.radion.database.connectionPool.ConnectionPool;
import ru.radion.dto.CompanyReadDto;
import ru.radion.service.CompanyService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@IT
public class CompanyServiceItTest extends TestMain{

    private static final Integer COMPANY_ID = 1;

    public CompanyServiceItTest(CompanyService companyService, DatabaseProperties databaseProperties, ConnectionPool pool1) {
        super(companyService, databaseProperties, pool1);
    }

//    @Test
//    void findById() {
//        Optional<CompanyReadDto> mayBeCompany = companyService.findById(COMPANY_ID);
//
//        assertTrue(mayBeCompany.isPresent());
//
//        CompanyReadDto companyReadDto = new CompanyReadDto(COMPANY_ID);
//
//        mayBeCompany.ifPresent(company -> assertEquals(companyReadDto, company));
//    }

}
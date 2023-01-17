package ru.radion.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.Assert;
import ru.radion.database.entity.Company;
import ru.radion.database.repository.CrudRepository;
import ru.radion.dto.CompanyReadDto;
import ru.radion.listener.entity.EntityEvent;
import ru.radion.service.CompanyService;
import ru.radion.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    private static final Integer COMPANY_ID = 1;
    @Mock
    private CrudRepository<Integer, Company> repository;
    @Mock
    private UserService service;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private CompanyService companyService;
    @Test
    void findById() {
        doReturn(Optional.of(new Company(COMPANY_ID)))
                .when(repository).findById((COMPANY_ID));

        Optional<CompanyReadDto> mayBeCompany = companyService.findById(COMPANY_ID);

        assertTrue(mayBeCompany.isPresent());

        CompanyReadDto companyReadDto = new CompanyReadDto(COMPANY_ID);

        mayBeCompany.ifPresent(company -> assertEquals(companyReadDto, company));

        verify(eventPublisher).publishEvent(any(EntityEvent.class));
        verifyNoMoreInteractions(eventPublisher, service);
    }
}
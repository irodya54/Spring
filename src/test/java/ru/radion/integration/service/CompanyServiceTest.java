package ru.radion.integration.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import ru.radion.database.entity.Company;
import ru.radion.database.repository.CompanyRepository;
import ru.radion.dto.CompanyReadDto;
import ru.radion.listener.entity.EntityEvent;
import ru.radion.service.CompanyService;
import ru.radion.service.UserService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    private static final Integer COMPANY_ID = 1;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private UserService service;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private CompanyService companyService;
    @Test
    void findById() {
        doReturn(Optional.of(new Company(COMPANY_ID, null, Collections.emptyMap())))
                .when(companyRepository).findById((COMPANY_ID));

        Optional<CompanyReadDto> mayBeCompany = companyService.findById(COMPANY_ID);

        assertTrue(mayBeCompany.isPresent());

        CompanyReadDto companyReadDto = new CompanyReadDto(COMPANY_ID, null);

        mayBeCompany.ifPresent(company -> assertEquals(companyReadDto, company));

        verify(eventPublisher).publishEvent(any(EntityEvent.class));
        verifyNoMoreInteractions(eventPublisher, service);
    }
}
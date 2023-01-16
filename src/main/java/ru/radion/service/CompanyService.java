package ru.radion.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.radion.database.entity.Company;
import ru.radion.database.repository.CrudRepository;
import ru.radion.dto.CompanyReadDto;
import ru.radion.listener.entity.AccessType;
import ru.radion.listener.entity.EntityEvent;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> repository;
    private final UserService service;
    private final ApplicationEventPublisher eventPublisher;

    public CompanyService(CrudRepository<Integer, Company> repository, UserService service, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.service = service;
        this.eventPublisher = eventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return repository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.getId());
                });
    }
}

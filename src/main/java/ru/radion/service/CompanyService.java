package ru.radion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.radion.database.repository.CompanyRepository;
import ru.radion.dto.CompanyReadDto;
import ru.radion.listener.entity.AccessType;
import ru.radion.listener.entity.EntityEvent;
import ru.radion.utils.mapper.CompanyMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service()
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return mapper.companyToCompanyReadDto(entity);
                });
    }

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream().map(mapper::companyToCompanyReadDto).collect(Collectors.toList());
    }
}

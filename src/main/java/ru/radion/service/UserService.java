package ru.radion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.radion.database.entity.Company;
import ru.radion.database.repository.CrudRepository;
import ru.radion.database.repository.UserRepository;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

}

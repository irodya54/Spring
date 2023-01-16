package ru.radion.service;

import org.springframework.stereotype.Service;
import ru.radion.database.entity.Company;
import ru.radion.database.repository.CrudRepository;
import ru.radion.database.repository.UserRepository;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository, CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}

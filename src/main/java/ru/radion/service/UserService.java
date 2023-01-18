package ru.radion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.radion.database.repository.CompanyRepository;
import ru.radion.database.repository.UserRepository;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

}

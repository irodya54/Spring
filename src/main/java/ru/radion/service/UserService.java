package ru.radion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.radion.database.entity.User;
import ru.radion.database.repository.CompanyRepository;
import ru.radion.database.repository.UserRepository;
import ru.radion.dto.UserReadDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    public UserReadDto findById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return new UserReadDto(byId.get().getId(), byId.get().getUsername());
    }

}

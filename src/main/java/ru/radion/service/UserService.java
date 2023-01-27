package ru.radion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.radion.database.entity.User;
import ru.radion.database.repository.CompanyRepository;
import ru.radion.database.repository.UserRepository;
import ru.radion.dto.UserCreateDto;
import ru.radion.dto.UserEditDto;
import ru.radion.dto.UserReadDto;
import ru.radion.utils.mapper.UserMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;
    private final UserMapper userMapper;

    public UserReadDto findAllFirstName(String firstname) {
        return userMapper.userToUserReadeDto(userRepository.findByFirstname(firstname));
    }

    public Page<UserReadDto> findAllBy(Pageable pageable) {
        return userRepository.findAllBy(pageable).map(userMapper::userToUserReadeDto);
    }
    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id).map(userMapper::userToUserReadeDto);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream().map(userMapper
                ::userToUserReadeDto).collect(Collectors.toList());
    }

    @Transactional
    public Optional<UserReadDto> create(UserCreateDto user) {
        return Optional.of(userRepository.save(userMapper.userCreateDtoToUser(user)))
                .map(userMapper::userToUserReadeDto);
    }

    @Transactional
    public Optional<UserReadDto> merge(Long id, UserEditDto userEditDto) {
        return Optional.of(userMapper.userEditDtoToUser(userEditDto))
                .map(user -> {
                    user.setId(id);
                    return userRepository.saveAndFlush(user);
                }).map(userMapper::userToUserReadeDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.deleteById(id);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public Optional<User> findByUsername (String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public List<User> findByBirthDate(LocalDate to, LocalDate from) {
        return userRepository.findByBirthDate(to, from);
    }
}

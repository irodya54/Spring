package ru.radion.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import ru.radion.IntegrationTestBase;
import ru.radion.annotation.IT;
import ru.radion.database.entity.Role;
import ru.radion.database.entity.User;
import ru.radion.dto.CompanyReadDto;
import ru.radion.dto.UserCreateDto;
import ru.radion.dto.UserEditDto;
import ru.radion.dto.UserReadDto;
import ru.radion.service.CompanyService;
import ru.radion.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class UserServiceItTest extends IntegrationTestBase {

    public static final Long USER_ID = 1L;


    private final UserService userService;
    private final CompanyService companyService;

    @Test
    void findByFirstname() {
        String firstname = "ov";
        UserReadDto allFirstName = userService.findAllFirstName(firstname);
        System.out.println();
    }

    @Test
    void findByBirthDate() {
        List<User> byBirthDate = userService.findByBirthDate(LocalDate.of(1990, 1, 1),
                LocalDate.of(2000, 1, 1));
        System.out.println(byBirthDate.get(0).getCompany().getName());
    }

    @Test
    void findAll() {
        List<UserReadDto> all = userService.findAll();

        assertFalse(all.isEmpty());
        assertThat(all).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> byId = userService.findById(USER_ID);

        assertThat(byId).isPresent();
        byId.ifPresent(user -> assertThat(user.getUsername()).isEqualTo("ivan@gmail.com"));
    }

    @Test
    void create() {
        UserCreateDto userCreateDto = new UserCreateDto("Radion@mail.ru",
                LocalDate.of(1991, 7, 26),
                "Radion",
                "Merzlyakov",
                Role.ADMIN,
                1);
        Optional<UserReadDto> createdUser = userService.create(userCreateDto);
        assertTrue(createdUser.isPresent());
        assertThat(createdUser.get().getUsername()).isEqualTo(userCreateDto.getUsername());
    }

    @Test
    void update() {
        CompanyReadDto company = companyService.findById(1).get();

        UserEditDto updateUser = new UserEditDto(
                "test@test.ru",
                LocalDate.of(2001, 1, 1),
                "testfirstname",
                "testlastname",
                Role.ADMIN,
                1
        );

        Optional<UserReadDto> mergeUser = userService.merge(USER_ID, updateUser);
        assertThat(mergeUser).isPresent();
        mergeUser.ifPresent(user -> {
            assertEquals(updateUser.getUsername(), user.getUsername());
            assertEquals(updateUser.getFirstname(), user.getFirstname());
            assertEquals(updateUser.getLastname(), user.getLastname());
            assertEquals(updateUser.getBirthDate(), user.getBirthDate());
            assertEquals(updateUser.getCompanyId(), user.getCompany().getId());
        });
    }

    @Test
    void delete() {
        Optional<UserReadDto> byId = userService.findById(USER_ID);
        assertTrue(byId.isPresent());
        assertTrue(userService.delete(USER_ID));
        assertTrue(userService.findById(USER_ID).isEmpty());
    }

}

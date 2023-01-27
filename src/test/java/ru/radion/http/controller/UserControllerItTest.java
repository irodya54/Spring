package ru.radion.http.controller;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.radion.IntegrationTestBase;
import ru.radion.database.entity.Role;
import ru.radion.database.entity.User;
import ru.radion.dto.UserCreateDto;
import ru.radion.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerItTest extends IntegrationTestBase {
    private final MockMvc mvc;
    private final UserService userService;
    @Test
    void findAll() throws Exception {
        mvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(5)));
    }

    @Test
    void findById() {
    }

    @Test
    void create() throws Exception {
        UserCreateDto user = new UserCreateDto(
                "test",
                LocalDate.of(2010, 11, 11),
                "testfistname",
                "testlastname",
                Role.ADMIN,
                1);
        mvc.perform(post("/api/v1/users")
                        .param("username", user.getUsername())
                        .param("firstname", user.getFirstname())
                        .param("lastname", user.getLastname())
                        .param("role", user.getRole().name())
                        .param("company_id", user.getCompany_id().toString())
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/api/v1/users{\\d+}")
                );
        Optional<User> returnUser = userService.findByUsername(user.getUsername());
        assertThat(returnUser).isPresent();
        returnUser.ifPresent(createdUser -> assertThat(createdUser.getFirstname()).isEqualTo(user.getFirstname()));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
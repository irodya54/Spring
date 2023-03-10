package ru.radion.dto;

import lombok.Value;
import ru.radion.database.entity.Role;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Long id;
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
    Integer company_id;

    CompanyReadDto company;
}

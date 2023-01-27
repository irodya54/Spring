package ru.radion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.radion.database.entity.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
public class UserEditDto {
    @NotNull(message = "не должен быть нал")
    String username;
    @NotNull(message = "не должен быть null тест")
    LocalDate birthDate;
    @NotEmpty(message = "не должен быть null тест")
    String firstname;
    @NotBlank(message = "не должен быть null тест")
    String lastname;
    @NotNull(message = "не должен быть null тест")
    Role role;
    Integer companyId;
}

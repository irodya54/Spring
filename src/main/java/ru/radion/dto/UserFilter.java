package ru.radion.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class PersonalInfo {

    String firstname;
    String lastname;
    LocalDate birthDate;

}

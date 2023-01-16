package ru.radion.service;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final UserService service;

    public CompanyService(UserService service) {
        this.service = service;
    }
}

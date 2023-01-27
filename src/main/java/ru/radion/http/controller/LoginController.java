package ru.radion.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.radion.dto.LoginDto;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String auth(LoginDto userReadDto) {

        return "redirect:/api/v1/test";
    }
}

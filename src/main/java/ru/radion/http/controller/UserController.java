package ru.radion.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.radion.database.entity.Role;
import ru.radion.dto.UserCreateDto;
import ru.radion.dto.UserEditDto;
import ru.radion.dto.UserReadDto;
import ru.radion.service.CompanyService;
import ru.radion.service.UserService;
import ru.radion.utils.mapper.UserMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;
    private final UserMapper userMapper;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById (Model model, @PathVariable("id") Long id) {
        return userService.findById(id)
                        .map(user -> {
                            model.addAttribute("user", userService.findById(id).get());
                            model.addAttribute("companies", companyService.findAll());
                            model.addAttribute("roles", Role.values());
                            return "user/user";
                        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Validated UserCreateDto userCreateDto) {
        Optional<UserReadDto> userReadDto = userService.create(userCreateDto);
        return "redirect:/api/v1/users" + userReadDto.get().getId();
    }

    @GetMapping("/create")
    public String createUser() {
        return "user/register-user";
    }

//    @PutMapping("/{Id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @Validated UserEditDto userEditDto) {
        System.out.println(id);
        System.out.println(userEditDto);
        return userService.merge(id, userEditDto)
                .map(it -> "redirect:/api/v1/users/")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }
}

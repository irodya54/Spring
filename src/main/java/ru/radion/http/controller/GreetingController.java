package ru.radion.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.radion.dto.LoginDto;
import ru.radion.dto.UserReadDto;
import ru.radion.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@SessionAttributes("user")
public class GreetingController {

    private final UserService userService;
    @GetMapping("hello/{id}")
    public String hello2(Model model,
                         @PathVariable Long id) {
        Optional<UserReadDto> user = userService.findById(id);
        model.addAttribute("user", user.get());
        return "hello";
    }


    @GetMapping("bye")
    public String bey(Model model,
                      @SessionAttribute("user") UserReadDto user) {
        model.addAttribute("user", user);
        return "bye";
    }

    @PostMapping("test")
    public String test(Model model,
                      LoginDto userReadDto) {
        model.addAttribute("user", userReadDto);
        return "test";
    }

//    @GetMapping("hello{id}")
//    public ModelAndView hello(ModelAndView modelAndView,
//                              @RequestParam Integer age,
//                              @RequestHeader String accept,
//                              @CookieValue("JSESSIONID") String jsessionId,
//                              @PathVariable Integer id) {
//        modelAndView.setViewName("hello");
//
//        return modelAndView;
//    }
}

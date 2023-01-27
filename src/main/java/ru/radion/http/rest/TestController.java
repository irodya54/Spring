package ru.radion.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.radion.dto.UserEditDto;
import ru.radion.dto.UserReadDto;
import ru.radion.service.UserService;
import ru.radion.utils.mapper.UserMapper;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/test")
public class TestController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public Page<UserReadDto> test(@RequestParam Integer offset,
                                  @RequestParam Integer limit) {
        Page<UserReadDto> allBy = userService.findAllBy(PageRequest.of(offset, limit));
        return allBy;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserReadDto> update(@PathVariable Long id,
                                              @RequestBody @Validated UserEditDto userEditDto) {
        Optional<UserReadDto> merge = userService.merge(id, userEditDto);

        return ResponseEntity.of(merge);
    }
}

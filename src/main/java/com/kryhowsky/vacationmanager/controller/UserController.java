package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.mapper.UserMapper;
import com.kryhowsky.vacationmanager.model.dto.UserDto;
import com.kryhowsky.vacationmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDto> getUsersPage(@RequestParam int page, @RequestParam int size) {
        return userService.getUsersPage(PageRequest.of(page, size)).map(userMapper::toDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto user) {
        return userMapper.toDto(userService.addUser(userMapper.toEntity(user)));
    }
}

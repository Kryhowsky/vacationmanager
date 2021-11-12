package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<User> getUsersPage(@RequestParam int page, @RequestParam int size) {
        return userService.getUsersPage(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}

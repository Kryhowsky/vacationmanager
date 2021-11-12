package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.model.Role;
import com.kryhowsky.vacationmanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Role> getRolesPage(@RequestParam int page, @RequestParam int size) {
        return roleService.getRolesPage(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

}

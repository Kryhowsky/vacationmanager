package com.kryhowsky.vacationmanager.service.impl;

import com.kryhowsky.vacationmanager.model.Role;
import com.kryhowsky.vacationmanager.repository.RoleRepository;
import com.kryhowsky.vacationmanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Page<Role> getRolesPage(int page, int size) {
        return roleRepository.findAll(PageRequest.of(page, size));
    }
}

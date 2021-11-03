package com.kryhowsky.vacationmanager.service;

import com.kryhowsky.vacationmanager.model.Role;
import org.springframework.data.domain.Page;

public interface RoleService {

    Role addRole(Role role);
    Page<Role> getRolesPage(int page, int size);

}

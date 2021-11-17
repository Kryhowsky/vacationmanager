package com.kryhowsky.vacationmanager.security;

import com.kryhowsky.vacationmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    public boolean hasAccessToUser(Long id) {
        return userService.getCurrentUser().getId().equals(id);
    }

}

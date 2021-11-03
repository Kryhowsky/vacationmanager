package com.kryhowsky.vacationmanager.service.impl;

import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.repository.RoleRepository;
import com.kryhowsky.vacationmanager.repository.UserRepository;
import com.kryhowsky.vacationmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User addUser(User user) {
        roleRepository.findByName("ROLE_USER").ifPresent(user::setRole);
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsersPage(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

}

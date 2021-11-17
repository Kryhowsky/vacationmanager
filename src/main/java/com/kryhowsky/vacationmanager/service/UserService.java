package com.kryhowsky.vacationmanager.service;

import com.kryhowsky.vacationmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Optional<User> getById(Long id);
    User addUser(User user);
    Page<User> getUsersPage(Pageable pageable);
    User getCurrentUser();

}

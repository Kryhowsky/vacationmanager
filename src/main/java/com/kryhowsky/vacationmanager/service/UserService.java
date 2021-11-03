package com.kryhowsky.vacationmanager.service;

import com.kryhowsky.vacationmanager.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

    User addUser(User user);
    Page<User> getUsersPage(int page, int size);

}

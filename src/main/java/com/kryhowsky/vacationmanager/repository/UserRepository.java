package com.kryhowsky.vacationmanager.repository;

import com.kryhowsky.vacationmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.kryhowsky.vacationmanager.repository;

import com.kryhowsky.vacationmanager.model.AvailableDays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvailableDaysRepository extends JpaRepository<AvailableDays, Long> {

    Optional<AvailableDays> findByUserIdAndYear(Long userId, Integer year);

}

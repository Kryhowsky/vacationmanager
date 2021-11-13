package com.kryhowsky.vacationmanager.service;

import com.kryhowsky.vacationmanager.model.AvailableDays;
import com.kryhowsky.vacationmanager.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AvailableDaysService {

    AvailableDays addAvailableDays(AvailableDays availableDays);
    Page<AvailableDays> getAvailableDaysPage(int page, int size);
    Optional<AvailableDays> getAvailableDaysByUserAndYear(User user, Integer year);

}

package com.kryhowsky.vacationmanager.service.impl;

import com.kryhowsky.vacationmanager.model.AvailableDays;
import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.repository.AvailableDaysRepository;
import com.kryhowsky.vacationmanager.service.AvailableDaysService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDaysServiceImpl implements AvailableDaysService {

    private final AvailableDaysRepository availableDaysRepository;

    @Override
    public AvailableDays addAvailableDays(AvailableDays availableDays) {
        return availableDaysRepository.save(availableDays);
    }

    @Override
    public Page<AvailableDays> getAvailableDaysPage(int page, int size) {
        return availableDaysRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<AvailableDays> getAvailableDaysByUserAndYear(User user, Integer year) {
        return availableDaysRepository.findByUserIdAndYear(user.getId(), year);
    }
}

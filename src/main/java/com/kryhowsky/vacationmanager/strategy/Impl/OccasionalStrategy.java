package com.kryhowsky.vacationmanager.strategy.Impl;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.service.AvailableDaysService;
import com.kryhowsky.vacationmanager.strategy.VacationRequestStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OccasionalStrategy implements VacationRequestStrategy {

    private final AvailableDaysService availableDaysService;

    @Override
    public boolean canBePlaced(VacationRequest vacationRequest) {

        var availableDaysForUser = availableDaysService
                .getAvailableDaysByUserAndYear(vacationRequest.getUser(), vacationRequest.getDayOfVacation().getYear());

        if (availableDaysForUser.isEmpty()) {
            return true;
        }

        return availableDaysForUser.get().getOccasionalDaysUsed() < 2;
    }

}

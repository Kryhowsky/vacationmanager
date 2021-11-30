package com.kryhowsky.vacationmanager.strategy.Impl;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationType;
import com.kryhowsky.vacationmanager.service.AvailableDaysService;
import com.kryhowsky.vacationmanager.strategy.VacationRequestStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeaveStrategy implements VacationRequestStrategy {

    private final AvailableDaysService availableDaysService;

    @Override
    public boolean canBePlaced(VacationRequest vacationRequest) {

        var availableDaysForUser = availableDaysService
                .getAvailableDaysByUserAndYear(vacationRequest.getUser(), vacationRequest.getDayOfVacation().getYear());

        if (availableDaysForUser.isEmpty()) {
            return true;
        }

        if (availableDaysForUser.get().getNumberOfAvailableDays() == 0 && vacationRequest.getDayOfVacation().getMonth().getValue() < 11) { // Brak wpisu nie ma dni do wykorzystania
            var availableDaysForUserPreviousYear = availableDaysService
                    .getAvailableDaysByUserAndYear(vacationRequest.getUser(), vacationRequest.getDayOfVacation().getYear() - 1);
        }

        return availableDaysForUser.get().getNumberOfAvailableDays() > 0;

    }

    @Override
    public VacationType getType() {
        return VacationType.LEAVE;
    }

}

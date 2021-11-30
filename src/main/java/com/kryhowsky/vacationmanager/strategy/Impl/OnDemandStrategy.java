package com.kryhowsky.vacationmanager.strategy.Impl;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationType;
import com.kryhowsky.vacationmanager.service.AvailableDaysService;
import com.kryhowsky.vacationmanager.strategy.VacationRequestStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnDemandStrategy implements VacationRequestStrategy {

    private final AvailableDaysService availableDaysService;

    @Override
    public boolean canBePlaced(VacationRequest vacationRequest) {

        var availableDaysForUser = availableDaysService
                .getAvailableDaysByUserAndYear(vacationRequest.getUser(), vacationRequest.getDayOfVacation().getYear());

        if (availableDaysForUser.isEmpty()) {
            return true;
        }

        return availableDaysForUser.get().getOnDemandDaysUsed() < 4 && availableDaysForUser.get().getNumberOfAvailableDays() > 0;

    }

    @Override
    public VacationType getType() {
        return VacationType.ON_DEMAND;
    }

}

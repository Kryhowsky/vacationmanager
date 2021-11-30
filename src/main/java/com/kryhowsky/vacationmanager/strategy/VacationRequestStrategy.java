package com.kryhowsky.vacationmanager.strategy;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationType;

public interface VacationRequestStrategy {

    boolean canBePlaced(VacationRequest vacationRequest);
    VacationType getType();

}

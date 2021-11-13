package com.kryhowsky.vacationmanager.strategy;

import com.kryhowsky.vacationmanager.model.VacationRequest;

public interface VacationRequestStrategy {

    boolean canBePlaced(VacationRequest vacationRequest);

}

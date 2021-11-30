package com.kryhowsky.vacationmanager.strategy;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationType;
import com.kryhowsky.vacationmanager.strategy.generic.GenericStrategy;

public interface VacationRequestStrategy extends GenericStrategy<VacationType> {

    boolean canBePlaced(VacationRequest vacationRequest);

}

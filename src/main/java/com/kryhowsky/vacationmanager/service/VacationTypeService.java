package com.kryhowsky.vacationmanager.service;

import com.kryhowsky.vacationmanager.model.VacationType;
import org.springframework.data.domain.Page;

public interface VacationTypeService {

    VacationType addVacationType(VacationType vacationType);
    Page<VacationType> getVacationTypesPage(int page, int size);

}

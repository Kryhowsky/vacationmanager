package com.kryhowsky.vacationmanager.service.impl;

import com.kryhowsky.vacationmanager.model.VacationType;
import com.kryhowsky.vacationmanager.repository.VacationTypeRepository;
import com.kryhowsky.vacationmanager.service.VacationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacationTypeServiceImpl implements VacationTypeService {

    private final VacationTypeRepository vacationTypeRepository;

    @Override
    public VacationType addVacationType(VacationType vacationType) {
        return vacationTypeRepository.save(vacationType);
    }

    @Override
    public Page<VacationType> getVacationTypesPage(int page, int size) {
        return vacationTypeRepository.findAll(PageRequest.of(page, size));
    }

}

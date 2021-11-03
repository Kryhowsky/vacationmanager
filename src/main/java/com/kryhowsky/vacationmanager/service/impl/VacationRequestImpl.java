package com.kryhowsky.vacationmanager.service.impl;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.repository.VacationRequestRepository;
import com.kryhowsky.vacationmanager.service.VacationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacationRequestImpl implements VacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;

    @Override
    public VacationRequest addVacationRequest(VacationRequest vacationRequest) {
        return vacationRequestRepository.save(vacationRequest);
    }
}

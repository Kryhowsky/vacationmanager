package com.kryhowsky.vacationmanager.service;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacationRequestService {

    VacationRequest addVacationRequest(VacationRequest vacationRequest);
    Page<VacationRequest> getVacationRequestsPage(Pageable pageable);
    void acceptVacationRequest(Long id);
    void rejectVacationRequest(Long id);
}

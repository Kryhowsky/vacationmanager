package com.kryhowsky.vacationmanager.service;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import org.springframework.data.domain.Page;

public interface VacationRequestService {

    VacationRequest addVacationRequest(VacationRequest vacationRequest);
    Page<VacationRequest> getVacationRequestsPage(int page, int size);
    void acceptVacationRequest(Long id);
    void rejectVacationRequest(Long id);
}

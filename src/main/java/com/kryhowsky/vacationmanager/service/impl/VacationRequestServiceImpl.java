package com.kryhowsky.vacationmanager.service.impl;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationRequestType;
import com.kryhowsky.vacationmanager.repository.UserRepository;
import com.kryhowsky.vacationmanager.repository.VacationRequestRepository;
import com.kryhowsky.vacationmanager.service.VacationRequestService;
import com.kryhowsky.vacationmanager.validator.VacationRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacationRequestServiceImpl implements VacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final UserRepository userRepository;
    private final VacationRequestValidator vacationRequestValidator;

    @Override
    public VacationRequest addVacationRequest(VacationRequest vacationRequest) {

        if (!vacationRequestValidator.isValid(vacationRequest)) {
            log.warn("Invalid request.");
            return vacationRequest;
        }

        vacationRequest.setVacationRequestType(VacationRequestType.PENDING);
        vacationRequest.setNumberOfDays((int) DAYS.between(vacationRequest.getStartDate(), vacationRequest.getEndDate()));
        return vacationRequestRepository.save(vacationRequest);
    }

    @Override
    public Page<VacationRequest> getVacationRequestsPage(int page, int size) {
        return vacationRequestRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public void acceptVacationRequest(Long id) {
        var vacationRequest = vacationRequestRepository.findById(id).orElseThrow();
        vacationRequest.setVacationRequestType(VacationRequestType.CONFIRMED);
    }

    @Override
    @Transactional
    public void rejectVacationRequest(Long id) {
        var vacationRequest = vacationRequestRepository.findById(id).orElseThrow();
        vacationRequest.setVacationRequestType(VacationRequestType.NOTCONFIRMED);
    }

}

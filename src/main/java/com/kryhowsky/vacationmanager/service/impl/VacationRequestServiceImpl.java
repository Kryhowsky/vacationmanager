package com.kryhowsky.vacationmanager.service.impl;

import com.kryhowsky.vacationmanager.model.*;
import com.kryhowsky.vacationmanager.repository.VacationRequestRepository;
import com.kryhowsky.vacationmanager.service.AvailableDaysService;
import com.kryhowsky.vacationmanager.service.UserService;
import com.kryhowsky.vacationmanager.service.VacationRequestService;
import com.kryhowsky.vacationmanager.strategy.generic.VacationRequestFactory;
import com.kryhowsky.vacationmanager.strategy.VacationRequestStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VacationRequestServiceImpl implements VacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final AvailableDaysService availableDaysService;
    private final UserService userService;
    private final VacationRequestFactory<VacationType, VacationRequestStrategy> vacationRequestFactory;

    @Override
    public VacationRequest addVacationRequest(VacationRequest vacationRequest) {

        var user = userService.getById(vacationRequest.getUser().getId()).orElseThrow(() -> new EntityNotFoundException("Not found."));
        var availableDaysForUser = availableDaysService.getAvailableDaysByUserAndYear(
                vacationRequest.getUser(), vacationRequest.getDayOfVacation().getYear());

        if (validateRequest(vacationRequest)) {

            AvailableDays availableDays;

            if (availableDaysForUser.isEmpty()) {

                availableDays = new AvailableDays();
                availableDays.setUser(user);
                availableDays.setYear(vacationRequest.getDayOfVacation().getYear());
                availableDays.setNumberOfAvailableDays(checkNumberOfPossibleDaysForUser(user));
                availableDays.setOnDemandDaysUsed(0);
                availableDays.setChildDaysUsed(0);
                availableDays.setOccasionalDaysUsed(0);

            } else {
                availableDays = availableDaysForUser.get();
            }

            vacationRequest.setVacationRequestStatus(VacationRequestStatus.PENDING);
            availableDaysService.addAvailableDays(availableDays);
            return vacationRequestRepository.save(vacationRequest);
        }

        return null; // TODO: CREATED STATUS IS RETURNED (status not accepted)
    }

    @Override
    public Page<VacationRequest> getVacationRequestsPage(Pageable pageable) {
        return vacationRequestRepository.findAll(pageable);
    }

    @Override
    public void acceptVacationRequest(Long id) {
        var vacationRequest = vacationRequestRepository.findById(id).orElseThrow();

        var availableDays = availableDaysService.getAvailableDaysByUserAndYear(
                vacationRequest.getUser(), vacationRequest.getDayOfVacation().getYear()).get();

        switch (vacationRequest.getVacationType()) {
            case LEAVE:
                availableDays.setNumberOfAvailableDays(availableDays.getNumberOfAvailableDays() - 1);
                break;

            case ON_DEMAND:
                availableDays.setNumberOfAvailableDays(availableDays.getNumberOfAvailableDays() - 1);
                availableDays.setOnDemandDaysUsed(availableDays.getOnDemandDaysUsed() + 1);
                break;

            case OCCASIONAL:
                availableDays.setOccasionalDaysUsed(availableDays.getOccasionalDaysUsed() + 1);
                break;

            case CHILD:
                availableDays.setChildDaysUsed(availableDays.getChildDaysUsed() + 1);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + vacationRequest.getVacationType());
        }

        vacationRequest.setVacationRequestStatus(VacationRequestStatus.CONFIRMED);
    }

    @Override
    public void rejectVacationRequest(Long id) {
        var vacationRequest = vacationRequestRepository.findById(id).orElseThrow();
        vacationRequest.setVacationRequestStatus(VacationRequestStatus.NOT_CONFIRMED);
    }

    private boolean validateRequest(VacationRequest vacationRequest) {

        VacationRequestStrategy vacationRequestStrategy = vacationRequestFactory.getStrategyByType(vacationRequest.getVacationType());

            if (vacationRequestStrategy.canBePlaced(vacationRequest)) {
                return true;
            } else {
                // TODO: ???
            }

        return vacationRequestStrategy.canBePlaced(vacationRequest);
    }

    private int checkNumberOfPossibleDaysForUser(User user) {
        return LocalDate.now().getYear() - user.getEmploymentDate().getYear() > 10 ? 26 : 20;
    }

}

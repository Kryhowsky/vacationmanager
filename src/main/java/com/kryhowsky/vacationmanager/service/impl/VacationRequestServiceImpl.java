package com.kryhowsky.vacationmanager.service.impl;

import com.kryhowsky.vacationmanager.model.AvailableDays;
import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationRequestStatus;
import com.kryhowsky.vacationmanager.repository.VacationRequestRepository;
import com.kryhowsky.vacationmanager.service.AvailableDaysService;
import com.kryhowsky.vacationmanager.service.UserService;
import com.kryhowsky.vacationmanager.service.VacationRequestService;
import com.kryhowsky.vacationmanager.strategy.Impl.ChildStrategy;
import com.kryhowsky.vacationmanager.strategy.Impl.LeaveStrategy;
import com.kryhowsky.vacationmanager.strategy.Impl.OccasionalStrategy;
import com.kryhowsky.vacationmanager.strategy.Impl.OnDemandStrategy;
import com.kryhowsky.vacationmanager.strategy.VacationRequestStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        return null; // TODO: CREATED STATUS IS RETURNED
    }

    @Override
    public Page<VacationRequest> getVacationRequestsPage(int page, int size) {
        return vacationRequestRepository.findAll(PageRequest.of(page, size));
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

        VacationRequestStrategy vacationRequestStrategy;

        switch (vacationRequest.getVacationType()) {
            case LEAVE:
                vacationRequestStrategy = new LeaveStrategy(availableDaysService);
                break;

            case ON_DEMAND:
                vacationRequestStrategy = new OnDemandStrategy(availableDaysService);
                break;

            case OCCASIONAL:
                vacationRequestStrategy = new OccasionalStrategy(availableDaysService);
                break;

            case CHILD:
                vacationRequestStrategy = new ChildStrategy(availableDaysService);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + vacationRequest.getVacationType());
        }

        return vacationRequestStrategy.canBePlaced(vacationRequest);
    }

    private int checkNumberOfPossibleDaysForUser(User user) {
        return LocalDate.now().getYear() - user.getEmploymentDate().getYear() > 10 ? 26 : 20;
    }

}

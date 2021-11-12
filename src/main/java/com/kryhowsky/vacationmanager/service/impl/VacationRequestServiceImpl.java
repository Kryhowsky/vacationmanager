package com.kryhowsky.vacationmanager.service.impl;

import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationRequestStatus;
import com.kryhowsky.vacationmanager.repository.UserRepository;
import com.kryhowsky.vacationmanager.repository.VacationRequestRepository;
import com.kryhowsky.vacationmanager.service.VacationRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VacationRequestServiceImpl implements VacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final UserRepository userRepository;
    private final VacationRequestValidator vacationRequestValidator;

    @Override
    public VacationRequest addVacationRequest(VacationRequest vacationRequest) {



//        vacationRequest.setVacationRequestStatus(VacationRequestStatus.PENDING);
//        vacationRequest.setNumberOfDays((int) DAYS.between(vacationRequest.getStartDate(), vacationRequest.getEndDate()));
//        return vacationRequestRepository.save(vacationRequest);
        return null;
    }

    @Override
    public Page<VacationRequest> getVacationRequestsPage(int page, int size) {
        return vacationRequestRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public void acceptVacationRequest(Long id) {
        var vacationRequest = vacationRequestRepository.findById(id).orElseThrow();
        vacationRequest.setVacationRequestStatus(VacationRequestStatus.CONFIRMED);
    }

    @Override
    public void rejectVacationRequest(Long id) {
        var vacationRequest = vacationRequestRepository.findById(id).orElseThrow();
        vacationRequest.setVacationRequestStatus(VacationRequestStatus.NOT_CONFIRMED);
    }

    private boolean validateRequest(VacationRequest vacationRequest) {

        var usersNumberOfPossibleDays = checkNumberOfPossibleDaysForUser(vacationRequest.getUser());
        var numberOfRequiredDays = checkNumberOfRequiredDaysInRequest(vacationRequest);

        if (vacationRequest.getVacationType().getName().equals("Occasional Holidays") || vacationRequest.getVacationType().getName().equals("Leave for a child")) {

        }

        var confirmedVacationRequests = vacationRequestRepository.findAllByUserAndVacationRequestStatus(vacationRequest.getUser(), VacationRequestStatus.CONFIRMED);

        var confirmedVacationRequestsForCurrentYear = confirmedVacationRequests.stream()
                .filter(request -> request.getEndDate().getYear() == LocalDate.now().getYear())
                .collect(Collectors.toList());

        return false;
    }

    private int checkNumberOfPossibleDaysForUser(User user) {
        return LocalDate.now().getYear() - user.getEmploymentDate().getYear() > 10 ? 26 : 20;
    }

    private int checkNumberOfRequiredDaysInRequest(VacationRequest vacationRequest) {
        return (int) DAYS.between(vacationRequest.getStartDate(), vacationRequest.getEndDate());
    }
}

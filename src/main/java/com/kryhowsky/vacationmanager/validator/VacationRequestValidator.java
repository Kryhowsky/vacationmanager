package com.kryhowsky.vacationmanager.validator;

import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationRequestType;
import com.kryhowsky.vacationmanager.repository.UserRepository;
import com.kryhowsky.vacationmanager.repository.VacationRequestRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Data
@Component
@RequiredArgsConstructor
public class VacationRequestValidator {

    private final VacationRequestRepository vacationRequestRepository;
    private final UserRepository userRepository;

    public boolean isValid(VacationRequest vacationRequest) {
        var user = userRepository.findById(vacationRequest.getUser().getId()).orElseThrow();
        var demandNumberOfDays = (int) DAYS.between(vacationRequest.getStartDate(), vacationRequest.getEndDate());

        var seniority = checkSeniority(user);
        var possibleNumberOfVacationDays = seniority > 10 ? 26 : 20;
        var numberOfDaysUsed = vacationRequestRepository.findAllByUserAndVacationRequestType(user, VacationRequestType.CONFIRMED); // TODO: wykluczyć "Occasional Holidays" i "Leave for a child".
        System.out.println(numberOfDaysUsed);

        return possibleNumberOfVacationDays - numberOfDaysUsed >= demandNumberOfDays && areDaysLeftAccordingToVacationType(vacationRequest, demandNumberOfDays);

    }

    public int checkSeniority(User user) {
        return LocalDate.now().getYear() - user.getEmploymentDate().getYear();
    }

    public boolean areDaysLeftAccordingToVacationType(VacationRequest vacationRequest, int demandNumberOfDays) {

        var numberOfDaysUsed = vacationRequestRepository.findAllByUserAndVacationRequestTypeAndVacationType(vacationRequest.getUser(), VacationRequestType.CONFIRMED, vacationRequest.getVacationType(), vacationRequest.getEndDate().getYear());

        switch (vacationRequest.getVacationType().getName()) {
            case "Occasional Holidays":
            case "Leave for a child":
                return demandNumberOfDays + numberOfDaysUsed <= 2;
            case "On Demand":
                return demandNumberOfDays + numberOfDaysUsed <= 4; // TODO: odjąć dni żądane z puli dostępnych dni
            default:
                return false;
        }
    }
}

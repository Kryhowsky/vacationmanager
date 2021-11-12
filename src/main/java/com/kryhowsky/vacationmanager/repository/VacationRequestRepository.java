package com.kryhowsky.vacationmanager.repository;

import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationRequestStatus;
import com.kryhowsky.vacationmanager.model.VacationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

    List<VacationRequest> findAllByUserEmailAndVacationRequestStatus(String userEmail, VacationRequestStatus vacationRequestStatus);

    List<VacationRequest> findAllByUserAndVacationRequestStatus(User user, VacationRequestStatus vacationRequestStatus);

    List<VacationRequest> findAllByUserAndVacationRequestStatusAndVacationType(User user, VacationRequestStatus vacationRequestStatus, VacationType vacationType);
}

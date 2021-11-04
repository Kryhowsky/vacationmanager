package com.kryhowsky.vacationmanager.repository;

import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.VacationRequestType;
import com.kryhowsky.vacationmanager.model.VacationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

    @Query("SELECT SUM(m.numberOfDays) FROM VacationRequest m")
    Long findAllByUserAndVacationRequestType(User user, VacationRequestType vacationRequestType);

    @Query("SELECT SUM(m.numberOfDays) FROM VacationRequest m WHERE m.user = :user and m.vacationRequestType = :vacationRequestType and m.vacationType = :vacationType and year(m.endDate) = :year")
    Long findAllByUserAndVacationRequestTypeAndVacationType(User user, VacationRequestType vacationRequestType, VacationType vacationType, int year);

}

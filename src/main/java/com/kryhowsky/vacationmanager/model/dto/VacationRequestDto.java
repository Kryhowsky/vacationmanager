package com.kryhowsky.vacationmanager.model.dto;

import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.model.VacationRequestStatus;
import com.kryhowsky.vacationmanager.model.VacationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestDto {

    private Long id;
    private User user;
    private LocalDate dayOfVacation;
    private VacationType vacationType;
    private VacationRequestStatus vacationRequestStatus;

}

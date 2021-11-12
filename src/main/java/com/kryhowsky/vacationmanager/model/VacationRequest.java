package com.kryhowsky.vacationmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequest { // dzień urlopu (LocalDate)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private VacationType vacationType;

    private VacationRequestStatus vacationRequestStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfDays;
}

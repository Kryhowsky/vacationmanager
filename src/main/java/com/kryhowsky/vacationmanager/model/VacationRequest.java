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
public class VacationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDate dayOfVacation;

    @Enumerated(EnumType.STRING)
    private VacationType vacationType;


    private VacationRequestStatus vacationRequestStatus;
}

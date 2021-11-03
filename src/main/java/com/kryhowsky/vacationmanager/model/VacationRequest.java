package com.kryhowsky.vacationmanager.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class VacationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private VacationType vacationType;

    private LocalDate startDate;
    private LocalDate endDate;
}

package com.kryhowsky.vacationmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;
    private Integer numberOfAvailableDays;
    private Integer onDemandDaysUsed;
    private Integer occasionalDaysUsed;
    private Integer childDaysUsed;

    @ManyToOne
    private User user;
}

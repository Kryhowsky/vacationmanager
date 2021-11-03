package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.service.VacationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacationrequests")
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;

    @PostMapping
    public ResponseEntity<VacationRequest> addVacationRequest(@RequestBody VacationRequest vacationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacationRequestService.addVacationRequest(vacationRequest));
    }

}

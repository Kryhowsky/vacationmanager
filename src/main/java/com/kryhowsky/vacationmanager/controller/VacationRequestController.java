package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.service.VacationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacationrequests")
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;

    @GetMapping
    public ResponseEntity<Page<VacationRequest>> getVacationRequestsPage(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.status(HttpStatus.OK).body(vacationRequestService.getVacationRequestsPage(page, size));
    }

    @PostMapping
    public ResponseEntity<VacationRequest> addVacationRequest(@RequestBody VacationRequest vacationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacationRequestService.addVacationRequest(vacationRequest));
    }

}

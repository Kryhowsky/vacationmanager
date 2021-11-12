package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.service.VacationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacationrequests")
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<VacationRequest> getVacationRequestsPage(@RequestParam int page, @RequestParam int size) {
        return vacationRequestService.getVacationRequestsPage(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacationRequest addVacationRequest(@RequestBody VacationRequest vacationRequest) {
        return vacationRequestService.addVacationRequest(vacationRequest);
    }

}

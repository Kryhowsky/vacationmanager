package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.mapper.VacationRequestMapper;
import com.kryhowsky.vacationmanager.model.dto.VacationRequestDto;
import com.kryhowsky.vacationmanager.service.VacationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacationrequests")
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;
    private final VacationRequestMapper vacationRequestMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<VacationRequestDto> getVacationRequestsPage(@RequestParam int page, @RequestParam int size) {
        return vacationRequestService.getVacationRequestsPage(PageRequest.of(page, size)).map(vacationRequestMapper::toDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacationRequestDto addVacationRequest(@RequestBody VacationRequestDto vacationRequest) {
        return vacationRequestMapper.toDto(vacationRequestService.addVacationRequest(vacationRequestMapper.toEntity(vacationRequest)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void acceptVacationRequest(@PathVariable Long id) {
        vacationRequestService.acceptVacationRequest(id);
    }

}

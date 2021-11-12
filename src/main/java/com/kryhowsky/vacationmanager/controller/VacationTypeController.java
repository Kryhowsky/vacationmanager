package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.model.VacationType;
import com.kryhowsky.vacationmanager.service.VacationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacationtypes")
public class VacationTypeController {

    private final VacationTypeService vacationTypeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<VacationType> getVacationTypesPage(@RequestParam int page, @RequestParam int size) {
        return vacationTypeService.getVacationTypesPage(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacationType addVacationType(@RequestBody VacationType vacationType) {
        return vacationTypeService.addVacationType(vacationType);
    }

}

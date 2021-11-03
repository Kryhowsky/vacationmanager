package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.model.VacationType;
import com.kryhowsky.vacationmanager.service.VacationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacationtypes")
public class VacationTypeController {

    private final VacationTypeService vacationTypeService;

    @GetMapping
    public ResponseEntity<Page<VacationType>> getVacationTypesPage(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.status(HttpStatus.OK).body(vacationTypeService.getVacationTypesPage(page, size));
    }

    @PostMapping
    public ResponseEntity<VacationType> addVacationType(@RequestBody VacationType vacationType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacationTypeService.addVacationType(vacationType));
    }

}

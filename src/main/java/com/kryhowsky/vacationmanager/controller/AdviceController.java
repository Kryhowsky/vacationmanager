package com.kryhowsky.vacationmanager.controller;

import com.kryhowsky.vacationmanager.exception.VacationRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VacationRequestException.class)
    public void handleVacationRequestException(VacationRequestException vacationRequestException) {

        log.warn("Invalid vacation request", vacationRequestException);

    }

}

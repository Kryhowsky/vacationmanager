package com.kryhowsky.vacationmanager.mapper;

import com.kryhowsky.vacationmanager.model.VacationRequest;
import com.kryhowsky.vacationmanager.model.dto.VacationRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VacationRequestMapper {

    VacationRequestDto toDto(VacationRequest vacationRequest);
    VacationRequest toEntity(VacationRequestDto vacationRequestDto);
}

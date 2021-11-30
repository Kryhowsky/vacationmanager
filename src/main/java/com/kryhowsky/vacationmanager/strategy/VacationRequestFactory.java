package com.kryhowsky.vacationmanager.strategy;

import com.kryhowsky.vacationmanager.model.VacationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VacationRequestFactory {

    private final List<VacationRequestStrategy> strategies;
    private Map<VacationType, VacationRequestStrategy> strategyMap;

    @PostConstruct
    void init() {
        strategyMap = strategies.stream()
                .collect(Collectors.toMap(VacationRequestStrategy::getType, Function.identity()));
    }

    public VacationRequestStrategy getStrategyByType(VacationType vacationType) {
        return strategyMap.get(vacationType);
    }

}

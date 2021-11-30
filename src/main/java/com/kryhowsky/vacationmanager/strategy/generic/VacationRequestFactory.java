package com.kryhowsky.vacationmanager.strategy.generic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VacationRequestFactory<K, V extends GenericStrategy<K>> {

    private final List<V> strategies;
    private Map<K, V> strategyMap;

    @PostConstruct
    void init() {
        strategyMap = strategies.stream()
                .collect(Collectors.toMap(V::getType, Function.identity()));
    }

    public V getStrategyByType(K type) {
        return strategyMap.get(type);
    }

}

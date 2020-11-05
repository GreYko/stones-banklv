package com.stones.stoneshomework.integration;

import com.stones.stoneshomework.integration.dto.FxRate;
import com.stones.stoneshomework.integration.dto.FxRatesResponse;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Profile("local")
public class LocalFxRateStub implements FxRateProvider {

    private final Map<LocalDate, FxRatesResponse> stubStorage;

    public LocalFxRateStub() {
        stubStorage = Map.of(
                LocalDate.of(2005, 3, 23),
                new FxRatesResponse("20050323", List.of(new FxRate("AUD", BigDecimal.valueOf(1.679))))
        );
    }

    public LocalFxRateStub(Map<LocalDate, FxRatesResponse> stubStorage) {
        this.stubStorage = stubStorage;
    }

    @Override
    public Optional<HashMap<String, BigDecimal>> getFxRates(LocalDate date) {
        FxRatesResponse temp = stubStorage.get(date);
        if (temp != null) {
            return Optional.of(temp.getFxRatesAsMap());
        }
        return Optional.empty();
    }
}

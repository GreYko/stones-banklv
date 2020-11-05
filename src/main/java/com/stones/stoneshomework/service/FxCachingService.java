package com.stones.stoneshomework.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // Default is singleton, but overridden for further awareness
public class FxCachingService {
    private final Cache<LocalDate, HashMap<String, BigDecimal>> cache = Caffeine.newBuilder()
            .build();

    public void storeFxRates(LocalDate date, HashMap<String, BigDecimal> rates) {
        cache.put(date, rates);
    }

    public Optional<BigDecimal> getFxRate(LocalDate date, String currencyCode) {
        String ccy = currencyCode.toUpperCase();
        if (ccy.equals("EUR")) {
            return Optional.of(BigDecimal.ONE);
        }

        HashMap<String, BigDecimal> t = cache.getIfPresent(date);
        if (t != null) {
            return Optional.ofNullable(t.get(ccy));
        }
        return Optional.empty();
    }
}

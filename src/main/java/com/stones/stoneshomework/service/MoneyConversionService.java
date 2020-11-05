package com.stones.stoneshomework.service;

import com.stones.stoneshomework.exception.ApplicationException;
import com.stones.stoneshomework.integration.FxRateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.stones.stoneshomework.util.Math.appMoneyRounding;
import static com.stones.stoneshomework.util.Math.calculationMathContext;

@Service
public class MoneyConversionService {
    private final FxCachingService cachingService;
    private final FxRateProvider fxRateProvider;

    public MoneyConversionService(
            @Autowired FxCachingService cachingService,
            @Autowired FxRateProvider fxRateProvider,
            @Value("${fxRates.cache.daysToPreLoad:0}") int daysToPreload) {
        this.cachingService = cachingService;
        this.fxRateProvider = fxRateProvider;
        if (daysToPreload > 0) {
            LocalDate now = LocalDate.now();
            // TODO: add logging with execution time measurement
            IntStream.range(0, daysToPreload).forEach(distance -> {
                LocalDate date = now.minusDays(distance);
                try {
                    fxRateProvider.getFxRates(date).ifPresent(it -> cachingService.storeFxRates(date, it));
                } catch (Exception e) {
                    // TODO: error handling to be improved - ensure constructor is still fail-proof
                }
            });
        }
    }

    private BigDecimal getFxRate(LocalDate date, String currencyCode) {
        Optional<BigDecimal> fxRate = cachingService.getFxRate(date, currencyCode);

        if (fxRate.isEmpty()) {
            // TODO: concurrency and locking
            Optional<HashMap<String, BigDecimal>> freshData = fxRateProvider.getFxRates(date);
            freshData.ifPresentOrElse(
                    it -> cachingService.storeFxRates(date, it),
                    () -> {
                        throw new ApplicationException(String.format("Unable to refresh live fx rates data for date: '%s'.", date));
                    }
            );
            fxRate = cachingService.getFxRate(date, currencyCode);
        }

        if (fxRate.isPresent()) {
            return fxRate.get();
        }

        throw new ApplicationException(String.format("Unable to retrieve fx rate data for date: '%s' and currency: '%s'.", date, currencyCode));
    }

    private BigDecimal convertToEur(LocalDate date, String currencyCode, BigDecimal amount) {
        BigDecimal rate = getFxRate(date, currencyCode);
        return amount.divide(rate, calculationMathContext);
    }

    public BigDecimal convert(LocalDate date, String fromCurrency, BigDecimal amount, String toCurrency) {
        BigDecimal rate = getFxRate(date, toCurrency);
        return convertToEur(date, fromCurrency, amount)
                .multiply(rate, calculationMathContext)
                .setScale(2, appMoneyRounding);
    }
}

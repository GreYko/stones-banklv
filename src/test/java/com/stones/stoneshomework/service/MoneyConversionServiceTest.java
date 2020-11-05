package com.stones.stoneshomework.service;

import com.stones.stoneshomework.integration.FxRateProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MoneyConversionServiceTest {

    @Test
    void convert() {
        LocalDate testDate = LocalDate.of(2015, 4, 14);
        String sourceCcy = "RUB";
        String targetCcy = "USD";

        FxCachingService cachingService = mock(FxCachingService.class);
        when(cachingService.getFxRate(testDate, sourceCcy)).thenReturn(Optional.of(BigDecimal.valueOf(32.0123)));
        when(cachingService.getFxRate(testDate, targetCcy)).thenReturn(Optional.of(BigDecimal.valueOf(0.91647)));

        FxRateProvider fxRatesService = mock(FxRateProvider.class);

        MoneyConversionService service = new MoneyConversionService(cachingService, fxRatesService, 0);

        assertEquals(15.46, service.convert(testDate, sourceCcy, BigDecimal.valueOf(540.12), targetCcy).doubleValue());

        verify(cachingService, times(2)).getFxRate(eq(testDate), any());
        verify(fxRatesService, times(0)).getFxRates(any());
    }
}

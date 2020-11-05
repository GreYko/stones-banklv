package com.stones.stoneshomework.integration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public interface FxRateProvider {
    Optional<HashMap<String, BigDecimal>> getFxRates(LocalDate date);
}

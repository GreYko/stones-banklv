package com.stones.stoneshomework.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FxRatesResponse {
    public final String date;
    public final List<FxRate> fxRates;

    public FxRatesResponse(
            @JsonProperty("Date") String date,
            @JsonProperty("Currencies") List<FxRate> fxRates
    ) {
        this.date = date;
        this.fxRates = Collections.unmodifiableList(fxRates);
    }

    public HashMap<String, BigDecimal> getFxRatesAsMap() {
        HashMap<String, BigDecimal> ratesMap = new HashMap<>();
        //rates.stream().collect(Collectors.toMap(BankLvCurrencyDto::getCurrencyCode, BankLvCurrencyDto::getRate, (prev, next) -> next, HashMap::new))
        fxRates.forEach(it -> ratesMap.put(it.currencyCode.toUpperCase(), it.rate));
        return ratesMap;
    }

}

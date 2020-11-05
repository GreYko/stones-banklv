package com.stones.stoneshomework.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FxRatesResponse {
    @JsonProperty("Date")
    public String date;
    @JsonProperty("Currencies")
    public List<FxRate> fxRates;

    public FxRatesResponse() {
    }

    public FxRatesResponse(String date, List<FxRate> fxRates) {
        this.date = date;
        this.fxRates = fxRates;
    }

    public HashMap<String, BigDecimal> getFxRatesAsMap() {
        HashMap<String, BigDecimal> ratesMap = new HashMap<>();
        //rates.stream().collect(Collectors.toMap(BankLvCurrencyDto::getCurrencyCode, BankLvCurrencyDto::getRate, (prev, next) -> next, HashMap::new))
        fxRates.forEach(it -> ratesMap.put(it.currencyCode.toUpperCase(), it.rate));
        return ratesMap;
    }

}

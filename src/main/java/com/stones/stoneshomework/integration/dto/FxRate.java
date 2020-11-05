package com.stones.stoneshomework.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

// TODO: would be great to have straight hashmap parsing here
public class FxRate {
    public final String currencyCode;
    public final BigDecimal rate;

    public FxRate(
            @JsonProperty("ID") String currencyCode,
            @JsonProperty("Rate") BigDecimal rate
    ) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }
}

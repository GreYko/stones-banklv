package com.stones.stoneshomework.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

// TODO: would be great to have straight hashmap parsing here
public class FxRate {
    @JsonProperty("ID")
    public String currencyCode;
    @JsonProperty("Rate")
    public BigDecimal rate;

    public FxRate() {
    }

    public FxRate(String currencyCode, BigDecimal rate) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }
}

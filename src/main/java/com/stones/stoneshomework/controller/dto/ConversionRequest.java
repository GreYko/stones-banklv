package com.stones.stoneshomework.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(example = "{\n" +
        "  \"date\": \"31.12.2020\",\n" +
        "  \"sourceCurrency\": \"BGE\",\n" +
        "  \"initialAmount\": 123.71,\n" +
        "  \"targetCurrency\": \"AUD\"\n" +
        "}")
public class ConversionRequest {
    private final static String inputDateFormat = "dd.MM.yyyy";

    public final LocalDate date;
    public final String sourceCurrency;
    public final BigDecimal initialAmount;
    public final String targetCurrency;

    public ConversionRequest(
            @JsonProperty("date")
            @JsonFormat(pattern = inputDateFormat)
            @Schema(description = "Date in '"+inputDateFormat+"' format", example = "31.12.2020")
            LocalDate date,
            @JsonProperty("sourceCurrency")
            String sourceCurrency,
            @JsonProperty("initialAmount")
            BigDecimal initialAmount,
            @JsonProperty("targetCurrency")
            String targetCurrency
    ) {
        this.date = date;
        this.sourceCurrency = sourceCurrency;
        this.initialAmount = initialAmount;
        this.targetCurrency = targetCurrency;
    }
}

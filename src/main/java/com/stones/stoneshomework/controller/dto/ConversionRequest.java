package com.stones.stoneshomework.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = inputDateFormat)
    @Schema(description = "Date in '"+inputDateFormat+"' format", example = "31.12.2020")
    public LocalDate date;
    public String sourceCurrency;
    public BigDecimal initialAmount;
    public String targetCurrency;
}

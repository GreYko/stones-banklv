package com.stones.stoneshomework.controller;

import com.stones.stoneshomework.controller.dto.ConversionRequest;
import com.stones.stoneshomework.service.MoneyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("")
public class MoneyConversionController {
    final MoneyConversionService conversionService;

    public MoneyConversionController(@Autowired MoneyConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/convert")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal convert(@RequestBody ConversionRequest body) {
        return conversionService.convert(body.date, body.sourceCurrency, body.initialAmount, body.targetCurrency);
    }

}

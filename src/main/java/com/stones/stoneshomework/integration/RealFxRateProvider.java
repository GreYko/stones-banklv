package com.stones.stoneshomework.integration;

import com.stones.stoneshomework.integration.dto.DtoFactory;
import com.stones.stoneshomework.integration.dto.FxRatesResponse;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

import static com.stones.stoneshomework.integration.dto.DtoFactory.parse;

// TODO: cover with real integration tests
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Profile("!local")
public class RealFxRateProvider implements FxRateProvider {
    private final DateTimeFormatter urlDateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final String url = "https://www.bank.lv/vk/ecb.xml?date=";


    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    protected String getUrlForDate(LocalDate date) {
        return url + date.format(urlDateFormat);
    }

    protected Optional<String> getRawData(LocalDate date) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(getUrlForDate(date)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return Optional.of(response.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<HashMap<String, BigDecimal>> getFxRates(LocalDate date) {
        Optional<String> rawData = getRawData(date);
        return rawData.flatMap(DtoFactory::parse)
                .map(FxRatesResponse::getFxRatesAsMap);
    }

}

package com.stones.stoneshomework.integration.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.Optional;

public class DtoFactory {

    private final static XmlMapper xmlMapper = new XmlMapper();

    public static Optional<FxRatesResponse> parse(String data) {
        try {
            return Optional.of(xmlMapper.readValue(data, FxRatesResponse.class));
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

package com.stones.stoneshomework.integration.dto;

import com.stones.stoneshomework.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DtoFactoryTest {

    private static final String TEST_RESPONSE = TestUtils.readResourceFile(DtoFactoryTest.class.getClassLoader(), "./samples/banklv-response.xml");


    @Test
    void testResponseParseable() {
        Optional<FxRatesResponse> value = DtoFactory.parse(TEST_RESPONSE);
        assertTrue(value.isPresent());
        assertEquals("20050323", value.get().date);
        assertEquals(33, value.get().fxRates.size());
        Optional<FxRate> zarFx = value.get().fxRates.stream().filter(fx -> fx.currencyCode.equals("ZAR")).findFirst();
        assertTrue(zarFx.isPresent());
        assertEquals(8.08790, zarFx.get().rate.doubleValue());
    }

}

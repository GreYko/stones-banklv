package com.stones.stoneshomework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "local") // Relies on Stub implementation. Remove line for real test.
public class MainReqsIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

//<Date>20050323</Date>
//<Currency>
//<ID>AUD</ID>
//<Rate>1.67900</Rate>
//</Currency>

    @Test
    public void testMainScenario() throws Exception {

        mockMvc.perform(post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"date\": \"23.03.2005\", \"sourceCurrency\": \"EUR\", \"initialAmount\": \"10.0\", \"targetCurrency\": \"AUD\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("16.79")));
    }

    @Test
    void testNoFxRateForGivenInput() throws Exception {
        mockMvc.perform(post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"date\": \"23.03.2005\", \"sourceCurrency\": \"EUR\", \"initialAmount\": \"10.0\", \"targetCurrency\": \"BGN\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(equalTo("Unable to retrieve fx rate data for date: '2005-03-23' and currency: 'BGN'.")));

    }

    @Test
    void testCannotReachFxRateProvider() throws Exception {
        mockMvc.perform(post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"date\": \"23.03.2007\", \"sourceCurrency\": \"EUR\", \"initialAmount\": \"10.0\", \"targetCurrency\": \"BGN\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(equalTo("Unable to refresh live fx rates data for date: '2007-03-23'.")));

    }
}

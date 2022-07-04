package com.lloyds.controller;

import com.lloyds.dto.ResponseBean;
import com.lloyds.service.AtmService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ATMControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("invalid url, should return 400(bad request)")
    @Test
    void shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/atmdetails").param("url", "https://api.lloydsbank.com/open-banking/v2.2/atms1").param("identification", "LF3DAC11"))
                .andDo(print())
                .andExpect(
                        status().isBadRequest());
    }

    @DisplayName("invalid id, should return 404(not found)")
    @Test
    void shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/atmdetails").param("url", "https://api.lloydsbank.com/open-banking/v2.2/atms").param("identification", "LF3DAC1111"))
                .andDo(print())
                .andExpect(
                        status().isNotFound());

    }

    @DisplayName("valid url and valid id, should return 200 OK")
    @Test
    void shouldReturnOK() throws Exception {
        mockMvc.perform(get("/api/v1/atmdetails").param("url", "https://api.lloydsbank.com/open-banking/v2.2/atms").param("identification", "LF3DAC11"))
                .andDo(print())
                .andExpect(
                        status().isOk())
                .andExpect(content().string(containsString("LF3DAC11")));

    }
}

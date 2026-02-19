package com.openclassroom.diabetes_risk;

import com.openclassroom.diabetes_risk.model.RiskReport;
import com.openclassroom.diabetes_risk.service.RiskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RiskController.class)
class RiskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RiskService riskService;

    @Test
    void shouldReturnRiskReport() throws Exception {
        RiskReport report = new RiskReport(1, "Test User", 40, "M", "Borderline");
        when(riskService.assessRisk(1)).thenReturn(report);

        mockMvc.perform(get("/risk/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.riskLevel").value("Borderline"))
                .andExpect(jsonPath("$.fullName").value("Test User"));
    }

    @Test
    void testEndpointShouldReturnOk() throws Exception {
        mockMvc.perform(get("/risk/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("diabetes-risk OK"));
    }
}
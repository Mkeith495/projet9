package com.openclassroom.diabetes_risk;

import com.openclassroom.diabetes_risk.model.Note;
import com.openclassroom.diabetes_risk.model.Patient;
import com.openclassroom.diabetes_risk.model.RiskReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class RiskServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RiskService riskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAssessRisk_EarlyOnset() {
        Patient mockPatient = new Patient();
        mockPatient.setPrenom("Test");
        mockPatient.setNom("User");
        mockPatient.setGenre("M");
        mockPatient.setDateNaissance("1980-01-01");

        Note note1 = new Note();
        note1.setContent("Poids, Taille, Fumeur, Anormal, Cholestérol, Vertiges, Rechute, Réaction");

        when(restTemplate.getForObject(anyString(), eq(Patient.class))).thenReturn(mockPatient);
        when(restTemplate.getForObject(anyString(), eq(Note[].class))).thenReturn(new Note[]{note1});

        RiskReport report = riskService.assessRisk(1);

        assertEquals("Early Onset", report.getRiskLevel());
    }
}
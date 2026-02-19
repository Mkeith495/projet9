package com.openclassroom.frontend;

import com.openclassroom.frontend.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(patientService, "gatewayUrl", "http://localhost:8082");
    }

    @Test
    void getAllPatients_ShouldReturnList() {
        Patient[] mockPatients = {new Patient(), new Patient()};
        when(restTemplate.getForObject(anyString(), eq(Patient[].class))).thenReturn(mockPatients);

        var result = patientService.getAllPatients();

        assertEquals(2, result.size());
        verify(restTemplate, times(1)).getForObject(contains("/patients"), eq(Patient[].class));
    }

    @Test
    void getPatientById_ShouldReturnPatient() {
        Patient mockPatient = new Patient();
        mockPatient.setId(1L);
        when(restTemplate.getForObject(anyString(), eq(Patient.class))).thenReturn(mockPatient);

        Patient result = patientService.getPatientById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
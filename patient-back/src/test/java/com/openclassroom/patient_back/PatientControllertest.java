package com.openclassroom.patient_back.controller;

import com.openclassroom.patient_back.model.Patient;
import com.openclassroom.patient_back.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService service;

    @Test
    void getPatientById_ShouldReturnOk() throws Exception {
        Patient p = new Patient();
        p.setId(1L);
        p.setNom("Test");

        when(service.getPatientById(1L)).thenReturn(Optional.of(p));

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Test"));
    }

    @Test
    void getPatientById_ShouldReturnNotFound() throws Exception {
        when(service.getPatientById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isNotFound());
    }
}
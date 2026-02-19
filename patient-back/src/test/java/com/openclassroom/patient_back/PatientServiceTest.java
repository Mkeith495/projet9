package com.openclassroom.patient_back.service;

import com.openclassroom.patient_back.model.Patient;
import com.openclassroom.patient_back.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository repo;

    @InjectMocks
    private PatientService patientService;

    @Test
    void createPatient_ShouldSavePatient() {
        Patient patient = new Patient();
        patient.setNom("Durand");
        when(repo.save(any(Patient.class))).thenReturn(patient);

        Patient saved = patientService.createPatient(new Patient());

        assertEquals("Durand", saved.getNom());
        verify(repo, times(1)).save(any());
    }

    @Test
    void deletePatient_ShouldReturnTrue_WhenExists() {
        when(repo.existsById(1L)).thenReturn(true);
        
        boolean deleted = patientService.deletePatient(1L);

        assertTrue(deleted);
        verify(repo, times(1)).deleteById(1L);
    }
}
package com.sprint2.doctor_notes.service;

import com.sprint2.doctor_notes.model.Note;
import com.sprint2.doctor_notes.model.Patient; 
import com.sprint2.doctor_notes.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final RestTemplate restTemplate; 

    public NoteService(NoteRepository noteRepository, RestTemplate restTemplate) {
        this.noteRepository = noteRepository;
        this.restTemplate = restTemplate;
    }

    public Note addNote(Note note) {
        String patientUrl = "http://patient-back:8081/patients/" + note.getPatientId();
        
        try {
            Patient patient = restTemplate.getForObject(patientUrl, Patient.class);
            
            if (patient == null) {
                throw new RuntimeException("Le patient n'existe pas.");
            }
        } catch (Exception e) {
            // Bloque la création si le patient est introuvable ou service offline
            throw new RuntimeException("Validation impossible : Le patient ID " + note.getPatientId() + " est introuvable.");
        }

        return noteRepository.save(note);
    }

    public List<Note> getNotesByPatient(Long patientId) {
        return noteRepository.findByPatientId(patientId);
    }
}
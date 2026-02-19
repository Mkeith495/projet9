package com.sprint2.doctor_notes;

import com.sprint2.doctor_notes.model.Note;
import com.sprint2.doctor_notes.model.Patient;
import com.sprint2.doctor_notes.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock private NoteRepository noteRepository;
    @Mock private RestTemplate restTemplate;
    @InjectMocks private NoteService noteService;

    @Test
    void addNote_ShouldSave_WhenPatientExists() {
        Note note = new Note();
        note.setPatientId("1");
        when(restTemplate.getForObject(anyString(), eq(Patient.class))).thenReturn(new Patient());
        when(noteRepository.save(note)).thenReturn(note);

        Note result = noteService.addNote(note);

        assertNotNull(result);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void addNote_ShouldThrowException_WhenPatientNotFound() {
        Note note = new Note();
        note.setPatientId("99");
        when(restTemplate.getForObject(anyString(), eq(Patient.class))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> noteService.addNote(note));
    }
}
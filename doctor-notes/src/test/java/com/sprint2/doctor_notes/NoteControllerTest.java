package com.sprint2.doctor_notes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint2.doctor_notes.model.Note;
import com.sprint2.doctor_notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateNote() throws Exception {
        Note note = new Note();
        note.setPatientId("1");
        note.setContent("Le patient se sent bien.");

        when(noteService.addNote(any(Note.class))).thenReturn(note);

        mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(note)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId").value("1"));
    }

    @Test
    void shouldGetNotesByPatientId() throws Exception {
        Note note = new Note();
        note.setPatientId("1");
        when(noteService.getNotesByPatient(1L)).thenReturn(List.of(note));

        mockMvc.perform(get("/notes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientId").value("1"));
    }
}
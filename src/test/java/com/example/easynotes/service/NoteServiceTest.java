package com.example.easynotes.service;

import com.example.easynotes.dto.NoteResponseWithAuthorDTO;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteService noteService;

    ModelMapper modelMapper;

    @Test
    void getAllNotes() {
        //Arrange
        Note note = new Note();
        note.setId(0L);
        note.setTitle("Si el tiempo no se me pasa más cuando se corta la luz1");
        Note note2 = new Note();
        note.setId(1L);
        note.setTitle("Si el tiempo no se me pasa más cuando se corta la luz2");
        List<Note> expectedNotes = Arrays.asList(note, note2);

        NoteResponseWithAuthorDTO noteDTO = modelMapper.map(note, NoteResponseWithAuthorDTO.class);
        NoteResponseWithAuthorDTO note2DTO =modelMapper.map(note2, NoteResponseWithAuthorDTO.class);

        List<NoteResponseWithAuthorDTO> expectedNotesDTO = Arrays.asList(noteDTO, note2DTO);

        //ACT && MOCK
        Mockito.when(noteRepository.findAll()).thenReturn(expectedNotes);
        List<NoteResponseWithAuthorDTO> foundNotes = noteService.getAllNotes();


        Assertions.assertEquals(expectedNotesDTO, foundNotes);

    }

    @Test
    void createNote() {
    }

    @Test
    void getNoteById() {
    }

    @Test
    void updateNote() {
    }

    @Test
    void deleteNote() {
    }

    @Test
    void addReviser() {
    }

    @Test
    void getThanks() {
    }

    @Test
    void getThreeMoreThankedNotes() {
    }

    @Test
    void getTypeNote() {
    }
}
package com.example.easynotes.service;

import com.example.easynotes.dto.NoteRequestDTO;
import com.example.easynotes.dto.ThankDTO;
import com.example.easynotes.dto.TypeNoteDTO;
import com.example.easynotes.dto.UserResponseDTO;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.model.Thank;
import com.example.easynotes.model.TypeNote;
import com.example.easynotes.model.User;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import javax.naming.spi.ResolveResult;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    NoteRepository noteRepository = Mockito.mock(NoteRepository.class);

    ModelMapper modelMapper = new ModelMapper();

    NoteService noteService = new NoteService(noteRepository, userRepository, modelMapper);

    @Test
    void getAllNotes() {
        noteService.getAllNotes();
    }

    @Test
    void createNote() {
        when(noteRepository.save(any(Note.class))).thenReturn(new Note());
        Assertions.assertDoesNotThrow(
                () -> noteService.createNote(new NoteRequestDTO()) );
    }

    @Test
    void getNoteById() {
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> noteService.getNoteById(1L) );
    }

    @Test
    void updateNote() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> noteService.updateNote(1L, new Note()));
    }

    @Test
    void updateNoteOk() {
        when(noteRepository.findById(1L)).thenReturn(Optional.of(new Note()));
        when(noteRepository.save(any(Note.class))).thenReturn(new Note());
        Assertions.assertDoesNotThrow(
                () -> noteService.updateNote(1L, new Note()));
    }

    @Test
    void deleteNote() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> noteService.deleteNote(1L) );
    }

    @Test
    void addReviser() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> noteService.addReviser(2L, 1L) );
    }


    @Test
    void addReviserOk() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(noteRepository.findById(2L)).thenReturn(Optional.of(new Note()));
        Assertions.assertDoesNotThrow(
                () -> noteService.addReviser(2L, 1L) );
    }

    @Test
    void getThanks() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> noteService.getThanks(3L) );
    }

    @Test
    void getThanksOk() {
        var pedro = new User();
        var note = new Note();
        note.setThanks(Set.of(new Thank(pedro, note)));
        when(noteRepository.findById(3L)).thenReturn(Optional.of(note));
        Assertions.assertDoesNotThrow(
                () -> noteService.getThanks(3L) );
    }

    @Test
    void getThreeMoreThankedNotes() {
        noteService.getThreeMoreThankedNotes(2020);
    }

    @Test
    void shouldGetTypeNoteOkNormal(){
        //Arrange
        Long id = 6L;
        Note note = new Note();
        User u1 = new User();
        User u2 = new User();

        note.setThanks(Set.of(new Thank(u1, note), new Thank(u2, note)));
        TypeNoteDTO expected = new TypeNoteDTO();
        expected.setCalification(TypeNote.Normal);

        Mockito.when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        //Act
        TypeNoteDTO current = noteService.getTypeNote(id);

        //Assert
        Assertions.assertEquals(expected, current);
    }

    @Test
    void shouldGetTypeNoteOkDeInteres(){
        //Arrange
        Long id = 6L;
        Note note = new Note();
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();
        User u4 = new User();
        User u5 = new User();
        User u6 = new User();
        u1.setId(11L);
        u2.setId(12L);
        u3.setId(13L);
        u4.setId(14L);
        u5.setId(15L);
        u6.setId(16L);

        note.setThanks(Set.of(new Thank(u1, note),
                new Thank(u2, note),
                new Thank(u3, note),
                new Thank(u4, note),
                new Thank(u5, note),
                new Thank(u6, note)
        ));
        TypeNoteDTO expected = new TypeNoteDTO();
        expected.setCalification(TypeNote.DeInteres);

        Mockito.when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        //Act
        TypeNoteDTO current = noteService.getTypeNote(id);

        //Assert
        Assertions.assertEquals(expected, current);
    }

    @Test
    void shouldGetTypeNoteOkDestacada(){
        //Arrange
        Long id = 6L;
        Note note = new Note();
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();
        User u4 = new User();
        User u5 = new User();
        User u6 = new User();
        User u7 = new User();
        User u8 = new User();
        User u9 = new User();
        User u10 = new User();
        User u11 = new User();
        u1.setId(11L);
        u2.setId(12L);
        u3.setId(13L);
        u4.setId(14L);
        u5.setId(15L);
        u6.setId(16L);
        u7.setId(17L);
        u8.setId(18L);
        u9.setId(19L);
        u10.setId(1L);
        u11.setId(2L);

        note.setThanks(Set.of(new Thank(u1, note),
                new Thank(u2, note),
                new Thank(u3, note),
                new Thank(u4, note),
                new Thank(u5, note),
                new Thank(u6, note),
                new Thank(u7, note),
                new Thank(u8, note),
                new Thank(u9, note),
                new Thank(u10, note),
                new Thank(u11, note)
        ));
        TypeNoteDTO expected = new TypeNoteDTO();
        expected.setCalification(TypeNote.Destacada);

        Mockito.when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        //Act
        TypeNoteDTO current = noteService.getTypeNote(id);

        //Assert
        Assertions.assertEquals(expected, current);
    }

    @Test
    void shouldGetTypeNoteNoOkDeInteres(){
        //Arrange
        Long id = 6L;
        Note note = new Note();
        User u1 = new User();
        User u2 = new User();

        note.setThanks(Set.of());
        TypeNoteDTO expected = new TypeNoteDTO();
        expected.setCalification(TypeNote.DeInteres);

        Mockito.when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        //Act
        TypeNoteDTO current = noteService.getTypeNote(id);

        //Assert
        Assertions.assertNotEquals(expected, current);
    }
}
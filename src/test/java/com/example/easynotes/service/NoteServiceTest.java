package com.example.easynotes.service;

import com.example.easynotes.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteService noteService;

    @Test
    void getAllNotes() {
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
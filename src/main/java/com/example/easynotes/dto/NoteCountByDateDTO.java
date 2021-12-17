package com.example.easynotes.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NoteCountByDateDTO {
    private int count;
    private LocalDate date;
}

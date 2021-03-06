package com.example.easynotes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String title;

    private String content;

    @ManyToMany(mappedBy = "revisedNotes")
    private Set<User> revisers = new HashSet<>();

    @OneToMany(mappedBy = "note")
    Set<Thank> thanks;

    @Column(nullable = false, updatable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDate createdAt;

    @Column(nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private LocalDate updatedAt;

}

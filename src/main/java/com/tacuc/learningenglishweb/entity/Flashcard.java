package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "flashcards")
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String word;
    @Column(nullable = false)
    private String meaning;
    @Column(columnDefinition = "TEXT")
    private String example1;
    @Column(columnDefinition = "TEXT")
    private String example2;
    public Flashcard() {
    }
    public Flashcard(String meaning, String word, String example2, String example1) {
        this.meaning = meaning;
        this.word = word;
        this.example2 = example2;
        this.example1 = example1;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flashcard)) return false;
        Flashcard flashcard = (Flashcard) o;
        return id != null && id.equals(flashcard.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

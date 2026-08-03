package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "flashcard_decks")
public class FlashcardDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    // 1 deck do đúng 1 user tạo — nhiều deck cùng trỏ về 1 user → @ManyToOne
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    // 1 deck chứa nhiều flashcard — mappedBy trỏ đúng tên field "deck" bên Flashcard.java
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flashcard> flashcards = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public FlashcardDeck() {
    }

    public FlashcardDeck(String name, User createdBy) {
        this.name = name;
        this.createdBy = createdBy;
    }

    // Helper method — đảm bảo đồng bộ 2 chiều, tránh quên add tay ở service
    public void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
        flashcard.setDeck(this);
    }

    public void removeFlashcard(Flashcard flashcard) {
        flashcards.remove(flashcard);
        flashcard.setDeck(null);
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlashcardDeck)) return false;
        FlashcardDeck that = (FlashcardDeck) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
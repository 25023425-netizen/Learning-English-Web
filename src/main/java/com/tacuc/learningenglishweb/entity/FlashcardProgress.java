package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "flashcard_progress", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "flashcard_id"})
)
public class FlashcardProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "flashcard_id", nullable = false)
    private Flashcard flashcard;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private FlashcardStatus flashcardStatus;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "last_reviewed_at")
    private LocalDateTime lastReviewedAt;
    @Column(name = "next_reviewed_at")
    private LocalDateTime nextReviewAt;
    @Column(name = "review_count", nullable = false)
    private int reviewCount = 0;
    public FlashcardProgress() {
    }
    public FlashcardProgress(Flashcard flashcard,User user ,FlashcardStatus flashcardStatus) {
        this.flashcard = flashcard;
        this.flashcardStatus = flashcardStatus;
        this.user = user;
    }
    @PrePersist
    public void onCreated(){
        this.createdAt= LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
    public void recordReview(FlashcardStatus newStatus, LocalDateTime nextReviewAt) {
        this.flashcardStatus = newStatus;
        this.reviewCount += 1;
        this.lastReviewedAt = LocalDateTime.now();
        this.nextReviewAt = nextReviewAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlashcardProgress)) return false;
        FlashcardProgress q = (FlashcardProgress) o;
        return id != null && id.equals(q.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

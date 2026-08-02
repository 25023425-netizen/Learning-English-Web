package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
@Entity
@Getter
@Setter
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Question question;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String context;
    @Column(name= "is_correct",nullable = false)
    private boolean isCorrect;
    public Option(String context, boolean isCorrect, Question question) {
        this.context = context;
        this.isCorrect = isCorrect;
        this.question = question;
    }
    public Option() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;
        Option q = (Option) o;
        return id != null && id.equals(q.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

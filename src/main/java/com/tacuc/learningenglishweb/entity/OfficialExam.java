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
@Table(name = "official_exams")
public class OfficialExam {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "official_exam_name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "officialExam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamQuestion> examQuestions = new ArrayList<>();
    @Column(name = "created_by", nullable = false)
    private User user;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfficialExam)) return false;
        OfficialExam officialExam = (OfficialExam) o;
        return id != null && id.equals(officialExam.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    public void addExamQuestion(Question question, int order, double score){
       ExamQuestion examQuestion = new ExamQuestion(this, question, order, score);
       examQuestions.add(examQuestion);
    }
}

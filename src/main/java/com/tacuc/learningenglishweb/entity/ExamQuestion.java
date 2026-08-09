package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "exam_questions")
public class ExamQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @Column(name = "score", nullable = false)
    private double score;
    @Column(name = "question_order", nullable = false)
    private int questionOrder;
    @ManyToOne
    @JoinColumn(name = "official_exam_id", nullable = false)
    private OfficialExam officialExam;
    public ExamQuestion() {
    }
    public ExamQuestion(OfficialExam officialExam, Question question, int questionOrder, double score) {
        this.officialExam = officialExam;
        this.question = question;
        this.questionOrder = questionOrder;
        this.score = score;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamQuestion)) return false;
        ExamQuestion examQuestion = (ExamQuestion) o;
        return id != null && id.equals(examQuestion.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

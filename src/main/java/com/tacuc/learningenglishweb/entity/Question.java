package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;
    @Setter(AccessLevel.NONE)
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> optionList = new ArrayList<>();
    public Question() {
    }
    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public Question(List<Option> optionList, String question) {
        this.optionList = optionList;
        this.question = question;
    }
    public void addOption(Option option){
        optionList.add(option);
        option.setQuestion(this);
    }
    public void removeOption(Option option){
        optionList.remove(option);
        option.setQuestion(null);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question q = (Question) o;
        return id != null && id.equals(q.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

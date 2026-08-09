package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lessons")
public class Lesson {
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}

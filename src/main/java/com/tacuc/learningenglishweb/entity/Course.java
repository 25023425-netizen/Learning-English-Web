package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "course_name")
    private String name;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Classroom> classrooms = new ArrayList<>();
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();
    public Course() {
    }
    @Column(name = "created_by", nullable = false)
    private User user;
    public Course(String name, User user) {
        this.name = name;
        this.user = user;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return id != null && id.equals(course.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    public void addLesson(Lesson lesson){
        lessons.add(lesson);
        lesson.setCourse(this);
    }
    public void removeLesson(Lesson lesson){
        lessons.remove(lesson);
        lesson.setCourse(null);
    }
}

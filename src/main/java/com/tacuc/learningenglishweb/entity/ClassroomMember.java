package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "classroom_members")
public class ClassroomMember {
    @ManyToOne
    @JoinColumn
    private Classroom classroom;
}

package com.tacuc.learningenglishweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(
        name = "classroom_members",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "classroom_id"})
)
public class ClassroomMember {
    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status = MemberStatus.PENDING;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private  User user;
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;
    public ClassroomMember() {
    }
    public ClassroomMember(Classroom classroom, MemberStatus status, User user) {
        this.classroom = classroom;
        this.status = status;
        this.user = user;
    }
    public ClassroomMember(User user, Classroom classroom) {
        this.user = user;
        this.classroom = classroom;
    }
    @PrePersist
    public void onCreate(){
        this.joinedAt = LocalDateTime.now();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassroomMember)) return false;
        ClassroomMember member = (ClassroomMember) o;
        return id != null && id.equals(member.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

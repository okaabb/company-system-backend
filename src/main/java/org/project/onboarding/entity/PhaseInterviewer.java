package org.project.onboarding.entity;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "phase_interviewer")
@AllArgsConstructor
@NoArgsConstructor
public class PhaseInterviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "phase_id", referencedColumnName = "id")
    private HiringPhase hiringPhase;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", referencedColumnName = "id")
    private Employee interviewer;

    @Column(name = "notes")
    private String notes;

    @Column(name = "score")
    private Float score;

    @Column(name = "created_at")
    @CreationTimestamp

    private LocalDate createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDate updatedAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

     @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;
    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (createdBy == null) createdBy = 1L;
        if (updatedBy == null) updatedBy = 1L;
    }

}

package org.project.onboarding.entity;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.project.onboarding.enums.PhaseNameEnum;
import org.project.onboarding.enums.PhaseNameEnum.*;
import org.project.onboarding.enums.PhaseStatusEnum;
import org.project.onboarding.enums.PhaseStatusEnum.*;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "hiring_phase")
@AllArgsConstructor
@NoArgsConstructor
public class HiringPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private PhaseNameEnum name;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

    @Column(name = "final_score")
    private Integer finalScore;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PhaseStatusEnum status;

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

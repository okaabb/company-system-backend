package org.project.onboarding.entity;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "training")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "id")
    private Employee creator;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "training_hours")
    private Float trainingHours;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDate updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

     @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;
    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (updatedBy == null) updatedBy = 1L;
    }
}

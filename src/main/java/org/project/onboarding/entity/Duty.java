package org.project.onboarding.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.project.onboarding.enums.DutyStatusEnum;
import org.project.onboarding.enums.DutyTypeEnum;

import java.time.LocalDate;

@Entity
@Data
@Builder
@Table(name = "duty")
@AllArgsConstructor
@NoArgsConstructor
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DutyTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "approved_by", referencedColumnName = "id")
    private Employee approvedByEmployee;

    @Column(name = "record_date")
    private LocalDate recordDate;

    @Column(name = "duty_date_from")
    private LocalDate dutyDateFrom;

    @Column(name = "duty_date_to")
    private LocalDate dutyDateTo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DutyStatusEnum status;

    @Column(name = "working_hours_per_day")
    private Float workingHoursPerDay;

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

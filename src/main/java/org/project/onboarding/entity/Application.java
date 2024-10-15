package org.project.onboarding.entity;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.project.onboarding.enums.ApplicationStatusEnum;
import org.project.onboarding.enums.ReferralTypeEnum;
import org.project.onboarding.enums.ApplyingPositionEnum;


import java.time.LocalDate;

@Table(name = "application")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "hr_id", referencedColumnName = "id")
    private Employee hr;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "referral_employee_id", referencedColumnName = "id")
    private Employee referralEmployee;

    @Column(name = "application_status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatusEnum applicationStatus;

    @Column(name = "linkedin_profile_url")
    private String linkedinProfileUrl;

    @Column(name = "referral_type")
    @Enumerated(EnumType.STRING)
    private ReferralTypeEnum referralType;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private ApplyingPositionEnum position;

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

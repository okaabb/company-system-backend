package org.project.onboarding.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.project.onboarding.enums.TeamMemberRoleEnum;
import org.project.onboarding.enums.TeamMemberRoleEnum.*;


@Entity
@Data
@Builder
@Table(name = "team_member")
@AllArgsConstructor
@NoArgsConstructor
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private TeamMemberRoleEnum role;

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

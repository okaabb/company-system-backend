package org.project.onboarding.entity;

import jakarta.annotation.PostConstruct;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Table(name = "applicant")
@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

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

    @Override
    public String toString() {
        return "Id: '" + this.id + "' "
                + "Name: '" + this.name + "' "
                + "Email: '" + this.email + "' "
                + "Mobile: '" + this.mobileNumber + "'";
    }

//    @PostConstruct
//    public void init() {
//        if (this.isDeleted == null) {
//            this.isDeleted = false;
//        }
//    }

}

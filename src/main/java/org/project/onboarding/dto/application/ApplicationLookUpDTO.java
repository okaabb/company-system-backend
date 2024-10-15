package org.project.onboarding.dto.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.dto.applicant.ApplicantDTO;
import org.project.onboarding.enums.ApplicationStatusEnum;
import org.project.onboarding.enums.ApplyingPositionEnum;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLookUpDTO {
    private Long id;
    private ApplicantDTO applicant;
    private ApplyingPositionEnum position;
    private ApplicationStatusEnum applicationStatus;
}

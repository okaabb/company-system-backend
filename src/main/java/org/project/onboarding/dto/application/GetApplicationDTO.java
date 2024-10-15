package org.project.onboarding.dto.application;

import lombok.*;
import org.project.onboarding.dto.applicant.ApplicantDTO;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.enums.ApplicationStatusEnum;
import org.project.onboarding.enums.ApplyingPositionEnum;
import org.project.onboarding.enums.ReferralTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetApplicationDTO {
    private ApplicantDTO applicant;
    private EmployeeDTO hr;
    private String department;
    private EmployeeDTO referralEmployee;
    private ApplicationStatusEnum applicationStatus;
    private String linkedinProfileUrl;
    private ReferralTypeEnum referralType;
    private ApplyingPositionEnum position;
}

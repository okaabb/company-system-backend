package org.project.onboarding.dto.application;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.project.onboarding.enums.ApplicationStatusEnum;
import org.project.onboarding.enums.ApplyingPositionEnum;
import org.project.onboarding.enums.ReferralTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostApplicationDTO {
    @NotNull(message = "Application must belong to an applicant.")
    private Long applicantId;

    private Long hrId;

    @NotNull(message = "Application must belong to a department.")
    private String departmentName;

    private Long referralEmployeeId;

    @NotNull(message = "Application must have a status.")
    private ApplicationStatusEnum applicationStatus;

    private String linkedinProfileUrl;

    private ReferralTypeEnum referralType;

    @NotNull(message = "Application must have a position.")
    private ApplyingPositionEnum position;
}

package org.project.onboarding.dto.application;

import lombok.Data;
import org.project.onboarding.enums.ApplicationStatusEnum;
import org.project.onboarding.enums.ReferralTypeEnum;

@Data
public class UpdateApplicationDTO {
    private Long hrId;
    private String departmentName;
    private Long referralEmployeeId;
    private ApplicationStatusEnum applicationStatus;
    private String linkedinProfileUrl;
}

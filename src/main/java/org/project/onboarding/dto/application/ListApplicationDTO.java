package org.project.onboarding.dto.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.enums.ApplicationStatusEnum;
import org.project.onboarding.enums.ApplyingPositionEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListApplicationDTO {
    private String applicantName;
    private String departmentName;
    private ApplicationStatusEnum applicationStatus;
    private ApplyingPositionEnum position;
}

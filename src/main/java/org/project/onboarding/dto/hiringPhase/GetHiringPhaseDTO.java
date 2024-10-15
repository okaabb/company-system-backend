package org.project.onboarding.dto.hiringPhase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.enums.PhaseNameEnum;
import org.project.onboarding.enums.PhaseStatusEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetHiringPhaseDTO {
    private PhaseNameEnum name;
    private Long applicationId;
    private Integer finalScore;
    private PhaseStatusEnum status;
}

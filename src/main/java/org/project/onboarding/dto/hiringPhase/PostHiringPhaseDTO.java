package org.project.onboarding.dto.hiringPhase;

import jakarta.validation.constraints.NotNull;
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
public class PostHiringPhaseDTO {
    @NotNull(message = "Hiring Phase must have a name.")
    private PhaseNameEnum name;

    @NotNull(message = "Hiring Phase must belong to an application.")
    private Long applicationId;

    private Integer finalScore;

    @NotNull(message = "Hiring Phase must have a status.")
    private PhaseStatusEnum status;
}

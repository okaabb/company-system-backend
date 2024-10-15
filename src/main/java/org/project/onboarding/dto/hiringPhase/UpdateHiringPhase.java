package org.project.onboarding.dto.hiringPhase;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.enums.PhaseNameEnum;
import org.project.onboarding.enums.PhaseStatusEnum;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHiringPhase {
    private PhaseNameEnum name;
    private Integer finalScore;
    private PhaseStatusEnum status;
}

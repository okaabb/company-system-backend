package org.project.onboarding.dto.hiringPhase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.enums.PhaseNameEnum;
import org.project.onboarding.enums.PhaseNameEnum.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class HiringPhaseLookUpDTO {
    private Long id;
    private PhaseNameEnum name;
    private Long applicationId;
}

package org.project.onboarding.dto.sprint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.dto.team.ListTeamDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSlimSprintDTO {
    private Long id;
    private String name;
    private ListTeamDTO teamId;
}

package org.project.onboarding.dto.teamMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.enums.TeamMemberRoleEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeamMemberDTO {
    private Long employeeId;
    private Long teamId;
    private TeamMemberRoleEnum role;

}

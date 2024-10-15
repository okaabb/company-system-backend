package org.project.onboarding.dto.teamMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.enums.TeamMemberRoleEnum;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AssignTeamMemberDTO {
    private TeamMemberRoleEnum role;
}

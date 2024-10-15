package org.project.onboarding.dto.teamMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.team.ListTeamDTO;
import org.project.onboarding.enums.TeamMemberRoleEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListTeamMemberDTO {
    private Long id;
    private EmployeeDTO employee;
    private ListTeamDTO team;
    private TeamMemberRoleEnum role;
}

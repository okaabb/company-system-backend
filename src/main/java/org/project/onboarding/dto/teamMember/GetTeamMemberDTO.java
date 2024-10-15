package org.project.onboarding.dto.teamMember;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.team.ListTeamDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetTeamMemberDTO extends AssignTeamMemberDTO {
    private EmployeeDTO employee;
    private ListTeamDTO team;
}

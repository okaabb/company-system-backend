package org.project.onboarding.dto.teamMember;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.enums.TeamMemberRoleEnum;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PostTeamMemberDTO {
    @NotNull(message = "Must have an employee.")
    private Long employeeId;

    @NotNull(message = "Must belong to a team.")
    private Long teamId;

    @NotNull(message = "Must have a role.")
    private TeamMemberRoleEnum role;
}

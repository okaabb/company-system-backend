package org.project.onboarding.dto.team;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTeamRequestDTO {
    @NotNull(message = "Team must have a name")
    private String name;

    @NotNull(message = "Team must belong to a department")
    private String department;

    private Long teamLeadId;
}

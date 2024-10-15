package org.project.onboarding.dto.sprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.team.ListTeamDTO;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetSprintDTO {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createdDate;
    private EmployeeDTO creator;
    private ListTeamDTO team;
}

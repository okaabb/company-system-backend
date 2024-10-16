package org.project.onboarding.mapper;

import javax.annotation.processing.Generated;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.team.ListTeamDTO;
import org.project.onboarding.dto.teamMember.GetTeamMemberDTO;
import org.project.onboarding.dto.teamMember.PostTeamMemberDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Team;
import org.project.onboarding.entity.TeamMember;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class TeamMemberMapperImpl implements TeamMemberMapper {

    @Override
    public TeamMember postToEntity(PostTeamMemberDTO updatedTeamMember) {
        if ( updatedTeamMember == null ) {
            return null;
        }

        TeamMember.TeamMemberBuilder teamMember = TeamMember.builder();

        teamMember.role( updatedTeamMember.getRole() );

        return teamMember.build();
    }

    @Override
    public GetTeamMemberDTO entityToGet(TeamMember teamMember) {
        if ( teamMember == null ) {
            return null;
        }

        GetTeamMemberDTO.GetTeamMemberDTOBuilder<?, ?> getTeamMemberDTO = GetTeamMemberDTO.builder();

        getTeamMemberDTO.role( teamMember.getRole() );
        getTeamMemberDTO.employee( employeeToEmployeeDTO( teamMember.getEmployee() ) );
        getTeamMemberDTO.team( teamToListTeamDTO( teamMember.getTeam() ) );

        return getTeamMemberDTO.build();
    }

    protected EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder<?, ?> employeeDTO = EmployeeDTO.builder();

        employeeDTO.id( employee.getId() );
        employeeDTO.name( employee.getName() );
        employeeDTO.email( employee.getEmail() );

        return employeeDTO.build();
    }

    protected ListTeamDTO teamToListTeamDTO(Team team) {
        if ( team == null ) {
            return null;
        }

        ListTeamDTO.ListTeamDTOBuilder listTeamDTO = ListTeamDTO.builder();

        listTeamDTO.id( team.getId() );
        listTeamDTO.name( team.getName() );

        return listTeamDTO.build();
    }
}

package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.department.DepartmentDTO;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.team.GetTeamResponseDTO;
import org.project.onboarding.dto.team.ListTeamDTO;
import org.project.onboarding.dto.team.PostTeamRequestDTO;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Team;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class TeamMapperImpl implements TeamMapper {

    @Override
    public GetTeamResponseDTO mapTeamEntityToGetTeamDTO(Team team) {
        if ( team == null ) {
            return null;
        }

        GetTeamResponseDTO.GetTeamResponseDTOBuilder getTeamResponseDTO = GetTeamResponseDTO.builder();

        getTeamResponseDTO.department( departmentToDepartmentDTO( team.getDepartment() ) );
        getTeamResponseDTO.name( team.getName() );
        getTeamResponseDTO.teamLead( employeeToEmployeeDTO( team.getTeamLead() ) );

        return getTeamResponseDTO.build();
    }

    @Override
    public Team mapPostTeamDTOtoTeamEntity(PostTeamRequestDTO updatedTeam) {
        if ( updatedTeam == null ) {
            return null;
        }

        Team.TeamBuilder team = Team.builder();

        team.name( updatedTeam.getName() );

        return team.build();
    }

    @Override
    public ListTeamDTO mapTeamEntityToListTeamDTO(Team team) {
        if ( team == null ) {
            return null;
        }

        ListTeamDTO.ListTeamDTOBuilder listTeamDTO = ListTeamDTO.builder();

        listTeamDTO.departmentName( teamDepartmentName( team ) );
        listTeamDTO.id( team.getId() );
        listTeamDTO.name( team.getName() );

        return listTeamDTO.build();
    }

    @Override
    public List<ListTeamDTO> mapTeamEntitiesToListTeamDTO(List<Team> teams) {
        if ( teams == null ) {
            return null;
        }

        List<ListTeamDTO> list = new ArrayList<ListTeamDTO>( teams.size() );
        for ( Team team : teams ) {
            list.add( mapTeamEntityToListTeamDTO( team ) );
        }

        return list;
    }

    @Override
    public List<ListTeamDTO> mapTeamEntitiesToListTeamDTOs(List<Team> teamList) {
        if ( teamList == null ) {
            return null;
        }

        List<ListTeamDTO> list = new ArrayList<ListTeamDTO>( teamList.size() );
        for ( Team team : teamList ) {
            list.add( mapTeamEntityToListTeamDTO( team ) );
        }

        return list;
    }

    private Long departmentManagerId(Department department) {
        if ( department == null ) {
            return null;
        }
        Employee manager = department.getManager();
        if ( manager == null ) {
            return null;
        }
        Long id = manager.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected DepartmentDTO departmentToDepartmentDTO(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDTO.DepartmentDTOBuilder<?, ?> departmentDTO = DepartmentDTO.builder();

        departmentDTO.managerId( departmentManagerId( department ) );
        departmentDTO.name( department.getName() );

        return departmentDTO.build();
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

    private String teamDepartmentName(Team team) {
        if ( team == null ) {
            return null;
        }
        Department department = team.getDepartment();
        if ( department == null ) {
            return null;
        }
        String name = department.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

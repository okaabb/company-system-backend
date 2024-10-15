package org.project.onboarding.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.teamMember.GetTeamMemberDTO;
import org.project.onboarding.dto.teamMember.PostTeamMemberDTO;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Team;
import org.springframework.stereotype.Component;
import org.project.onboarding.entity.TeamMember;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeamMemberMapper {
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "employee", ignore = true)
    TeamMember postToEntity(PostTeamMemberDTO updatedTeamMember);

    GetTeamMemberDTO entityToGet(TeamMember teamMember);

    default Long mapEmployee(Employee value) {
        return value.getId();
    }
    default String map(Department value) {
        return value.getName();
    }
    default Long mapTeam(Team value) {
        return value.getId();
    }
}

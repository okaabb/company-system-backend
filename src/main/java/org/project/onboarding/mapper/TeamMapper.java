package org.project.onboarding.mapper;

import org.mapstruct.*;

import org.project.onboarding.dto.team.GetTeamResponseDTO;
import org.project.onboarding.dto.team.ListTeamDTO;
import org.project.onboarding.dto.team.PostTeamRequestDTO;
import org.project.onboarding.entity.Team;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeamMapper {
    @Mapping(target = "department.managerId", source = "team.department.manager.id")
    GetTeamResponseDTO mapTeamEntityToGetTeamDTO(Team team);

    @Mapping(target = "teamLead", ignore = true)
    @Mapping(target = "department", ignore = true)
    Team mapPostTeamDTOtoTeamEntity(PostTeamRequestDTO updatedTeam);

    @Mapping(target = "departmentName", source = "team.department.name")
    ListTeamDTO mapTeamEntityToListTeamDTO(Team team);

    List<ListTeamDTO> mapTeamEntitiesToListTeamDTO(List<Team> teams);

    List<ListTeamDTO> mapTeamEntitiesToListTeamDTOs(List<Team> teamList);
}

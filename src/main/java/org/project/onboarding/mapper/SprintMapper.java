package org.project.onboarding.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.sprint.*;
import org.project.onboarding.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SprintMapper {
    GetSprintDTO mapSprintEntityToGetSprintDTO(Sprint sprint);

    @Mapping(target = "team", ignore = true)
    @Mapping(target = "sprintCreator", ignore = true)
    Sprint mapPostSprintDTOtoSprintDTO(PostSprintDTO dto);

    Sprint mapUpdateSprintDTOtoSprintDTO(UpdateSprintDTO dto);

    List<ListSprintDTO> mapSprintEntitiesToListSprintDTOs(List<Sprint> sprintList);

    ListSprintDTO mapSprintEntityToSListSprintDTO(Sprint sprint);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void copyFields(Sprint updatedSprint, @MappingTarget Sprint sprint);

}

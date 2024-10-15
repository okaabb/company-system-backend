package org.project.onboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.hiringPhase.*;
import org.project.onboarding.entity.HiringPhase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HiringPhaseMapper {
    HiringPhase mapHiringPhasePostDTOtoHiringPhaseEntity(PostHiringPhaseDTO dto);

    GetHiringPhaseDTO mapHiringPhaseEntityToHiringPhaseGetDTO(HiringPhase hiringPhase);

    List<ListHiringPhaseDTO> mapHiringPhaseEntitiesToListHiringPhaseDTOs(List<HiringPhase> hiringPhaseList);

    ListHiringPhaseDTO mapHiringPhaseEntityToListHiringPhaseDTO(HiringPhase hiringPhase);

    HiringPhase mapHiringPhaseUpdateDTOtoHiringPhaseEntity(UpdateHiringPhase dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void copyFields(HiringPhase updatedHiringPhase, @MappingTarget HiringPhase hiringPhase);
}

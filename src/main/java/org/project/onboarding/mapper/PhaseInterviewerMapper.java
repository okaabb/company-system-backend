package org.project.onboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.phaseInterviewer.*;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.PhaseInterviewer;
import org.project.onboarding.entity.HiringPhase;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PhaseInterviewerMapper {
    @Mapping(target = "hiringPhase", ignore = true)
    @Mapping(target = "interviewer", ignore = true)
    PhaseInterviewer mapPostPhaseInterviewerDTOtoPhaseInterviewerEntity(PostPhaseInterviewerDTO dto);

    GetPhaseInterviewerDTO mapPhaseInterviewerEntityToGetPhaseInterviewerDTO(PhaseInterviewer phaseInterviewer);

    List<ListPhaseInterviewerDTO> mapPhaseInterviewerEntitiesToListPhaseInterviewerDTOs(List<PhaseInterviewer> phaseInterviewerList);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void copyFields(PhaseInterviewer updatedPhaseInterviewer, @MappingTarget PhaseInterviewer phaseInterviewer);

    @Mapping(target = "hiringPhase", ignore = true)
    @Mapping(target = "interviewer", ignore = true)
    PhaseInterviewer mapUpdatePhaseInterviewerDTOtoPhaseInterviewerEntity(UpdatePhaseInterviewerDTO dto);
}

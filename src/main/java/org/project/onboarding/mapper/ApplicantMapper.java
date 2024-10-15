package org.project.onboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.applicant.ApplicantDTO;
import org.project.onboarding.dto.applicant.GetApplicantResponseDTO;
import org.project.onboarding.dto.applicant.ListApplicantsResponseDTO;
import org.project.onboarding.dto.applicant.UpdateApplicantDTO;
import org.project.onboarding.entity.Applicant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApplicantMapper {
    GetApplicantResponseDTO mapEntityToGetResponse(Applicant applicant);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void copyFields(Applicant updatedApplicant, @MappingTarget Applicant applicant);

    Applicant mapPostRequestToEntity(ApplicantDTO dto);

    Applicant mapUpdateRequestToEntity(UpdateApplicantDTO dto);

    List<ListApplicantsResponseDTO> mapApplicantEntitiesToListApplicantDTOs(List<Applicant> applicantList);
}

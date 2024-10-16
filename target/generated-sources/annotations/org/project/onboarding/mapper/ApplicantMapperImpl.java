package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.applicant.ApplicantDTO;
import org.project.onboarding.dto.applicant.GetApplicantResponseDTO;
import org.project.onboarding.dto.applicant.ListApplicantsResponseDTO;
import org.project.onboarding.dto.applicant.UpdateApplicantDTO;
import org.project.onboarding.entity.Applicant;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ApplicantMapperImpl implements ApplicantMapper {

    @Override
    public GetApplicantResponseDTO mapEntityToGetResponse(Applicant applicant) {
        if ( applicant == null ) {
            return null;
        }

        GetApplicantResponseDTO.GetApplicantResponseDTOBuilder getApplicantResponseDTO = GetApplicantResponseDTO.builder();

        getApplicantResponseDTO.name( applicant.getName() );
        getApplicantResponseDTO.email( applicant.getEmail() );
        getApplicantResponseDTO.mobileNumber( applicant.getMobileNumber() );

        return getApplicantResponseDTO.build();
    }

    @Override
    public void copyFields(Applicant updatedApplicant, Applicant applicant) {
        if ( updatedApplicant == null ) {
            return;
        }

        if ( updatedApplicant.getId() != null ) {
            applicant.setId( updatedApplicant.getId() );
        }
        if ( updatedApplicant.getName() != null ) {
            applicant.setName( updatedApplicant.getName() );
        }
        if ( updatedApplicant.getEmail() != null ) {
            applicant.setEmail( updatedApplicant.getEmail() );
        }
        if ( updatedApplicant.getMobileNumber() != null ) {
            applicant.setMobileNumber( updatedApplicant.getMobileNumber() );
        }
        if ( updatedApplicant.getUpdatedAt() != null ) {
            applicant.setUpdatedAt( updatedApplicant.getUpdatedAt() );
        }
        if ( updatedApplicant.getUpdatedBy() != null ) {
            applicant.setUpdatedBy( updatedApplicant.getUpdatedBy() );
        }
        if ( updatedApplicant.getIsDeleted() != null ) {
            applicant.setIsDeleted( updatedApplicant.getIsDeleted() );
        }
    }

    @Override
    public Applicant mapPostRequestToEntity(ApplicantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Applicant.ApplicantBuilder applicant = Applicant.builder();

        applicant.name( dto.getName() );
        applicant.email( dto.getEmail() );
        applicant.mobileNumber( dto.getMobileNumber() );

        return applicant.build();
    }

    @Override
    public Applicant mapUpdateRequestToEntity(UpdateApplicantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Applicant.ApplicantBuilder applicant = Applicant.builder();

        applicant.name( dto.getName() );
        applicant.email( dto.getEmail() );
        applicant.mobileNumber( dto.getMobileNumber() );

        return applicant.build();
    }

    @Override
    public List<ListApplicantsResponseDTO> mapApplicantEntitiesToListApplicantDTOs(List<Applicant> applicantList) {
        if ( applicantList == null ) {
            return null;
        }

        List<ListApplicantsResponseDTO> list = new ArrayList<ListApplicantsResponseDTO>( applicantList.size() );
        for ( Applicant applicant : applicantList ) {
            list.add( applicantToListApplicantsResponseDTO( applicant ) );
        }

        return list;
    }

    protected ListApplicantsResponseDTO applicantToListApplicantsResponseDTO(Applicant applicant) {
        if ( applicant == null ) {
            return null;
        }

        ListApplicantsResponseDTO.ListApplicantsResponseDTOBuilder<?, ?> listApplicantsResponseDTO = ListApplicantsResponseDTO.builder();

        listApplicantsResponseDTO.name( applicant.getName() );
        listApplicantsResponseDTO.email( applicant.getEmail() );
        listApplicantsResponseDTO.mobileNumber( applicant.getMobileNumber() );
        listApplicantsResponseDTO.id( applicant.getId() );

        return listApplicantsResponseDTO.build();
    }
}

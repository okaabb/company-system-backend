package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.hiringPhase.GetHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.ListHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.PostHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.UpdateHiringPhase;
import org.project.onboarding.entity.HiringPhase;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:25+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class HiringPhaseMapperImpl implements HiringPhaseMapper {

    @Override
    public HiringPhase mapHiringPhasePostDTOtoHiringPhaseEntity(PostHiringPhaseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        HiringPhase.HiringPhaseBuilder hiringPhase = HiringPhase.builder();

        hiringPhase.name( dto.getName() );
        hiringPhase.finalScore( dto.getFinalScore() );
        hiringPhase.status( dto.getStatus() );

        return hiringPhase.build();
    }

    @Override
    public GetHiringPhaseDTO mapHiringPhaseEntityToHiringPhaseGetDTO(HiringPhase hiringPhase) {
        if ( hiringPhase == null ) {
            return null;
        }

        GetHiringPhaseDTO.GetHiringPhaseDTOBuilder getHiringPhaseDTO = GetHiringPhaseDTO.builder();

        getHiringPhaseDTO.name( hiringPhase.getName() );
        getHiringPhaseDTO.finalScore( hiringPhase.getFinalScore() );
        getHiringPhaseDTO.status( hiringPhase.getStatus() );

        return getHiringPhaseDTO.build();
    }

    @Override
    public List<ListHiringPhaseDTO> mapHiringPhaseEntitiesToListHiringPhaseDTOs(List<HiringPhase> hiringPhaseList) {
        if ( hiringPhaseList == null ) {
            return null;
        }

        List<ListHiringPhaseDTO> list = new ArrayList<ListHiringPhaseDTO>( hiringPhaseList.size() );
        for ( HiringPhase hiringPhase : hiringPhaseList ) {
            list.add( mapHiringPhaseEntityToListHiringPhaseDTO( hiringPhase ) );
        }

        return list;
    }

    @Override
    public ListHiringPhaseDTO mapHiringPhaseEntityToListHiringPhaseDTO(HiringPhase hiringPhase) {
        if ( hiringPhase == null ) {
            return null;
        }

        ListHiringPhaseDTO listHiringPhaseDTO = new ListHiringPhaseDTO();

        return listHiringPhaseDTO;
    }

    @Override
    public HiringPhase mapHiringPhaseUpdateDTOtoHiringPhaseEntity(UpdateHiringPhase dto) {
        if ( dto == null ) {
            return null;
        }

        HiringPhase.HiringPhaseBuilder hiringPhase = HiringPhase.builder();

        hiringPhase.name( dto.getName() );
        hiringPhase.finalScore( dto.getFinalScore() );
        hiringPhase.status( dto.getStatus() );

        return hiringPhase.build();
    }

    @Override
    public void copyFields(HiringPhase updatedHiringPhase, HiringPhase hiringPhase) {
        if ( updatedHiringPhase == null ) {
            return;
        }

        if ( updatedHiringPhase.getId() != null ) {
            hiringPhase.setId( updatedHiringPhase.getId() );
        }
        if ( updatedHiringPhase.getName() != null ) {
            hiringPhase.setName( updatedHiringPhase.getName() );
        }
        if ( updatedHiringPhase.getApplication() != null ) {
            hiringPhase.setApplication( updatedHiringPhase.getApplication() );
        }
        if ( updatedHiringPhase.getFinalScore() != null ) {
            hiringPhase.setFinalScore( updatedHiringPhase.getFinalScore() );
        }
        if ( updatedHiringPhase.getStatus() != null ) {
            hiringPhase.setStatus( updatedHiringPhase.getStatus() );
        }
        if ( updatedHiringPhase.getUpdatedAt() != null ) {
            hiringPhase.setUpdatedAt( updatedHiringPhase.getUpdatedAt() );
        }
        if ( updatedHiringPhase.getUpdatedBy() != null ) {
            hiringPhase.setUpdatedBy( updatedHiringPhase.getUpdatedBy() );
        }
        if ( updatedHiringPhase.getIsDeleted() != null ) {
            hiringPhase.setIsDeleted( updatedHiringPhase.getIsDeleted() );
        }
    }
}

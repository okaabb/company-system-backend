package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.sprint.GetSprintDTO;
import org.project.onboarding.dto.sprint.ListSprintDTO;
import org.project.onboarding.dto.sprint.PostSprintDTO;
import org.project.onboarding.dto.sprint.UpdateSprintDTO;
import org.project.onboarding.dto.team.ListTeamDTO;
import org.project.onboarding.entity.Sprint;
import org.project.onboarding.entity.Team;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class SprintMapperImpl implements SprintMapper {

    @Override
    public GetSprintDTO mapSprintEntityToGetSprintDTO(Sprint sprint) {
        if ( sprint == null ) {
            return null;
        }

        GetSprintDTO.GetSprintDTOBuilder<?, ?> getSprintDTO = GetSprintDTO.builder();

        getSprintDTO.name( sprint.getName() );
        getSprintDTO.startDate( sprint.getStartDate() );
        getSprintDTO.endDate( sprint.getEndDate() );
        getSprintDTO.createdDate( sprint.getCreatedDate() );
        getSprintDTO.team( teamToListTeamDTO( sprint.getTeam() ) );

        return getSprintDTO.build();
    }

    @Override
    public Sprint mapPostSprintDTOtoSprintDTO(PostSprintDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Sprint.SprintBuilder sprint = Sprint.builder();

        sprint.name( dto.getName() );
        sprint.startDate( dto.getStartDate() );
        sprint.createdDate( dto.getCreatedDate() );
        sprint.endDate( dto.getEndDate() );

        return sprint.build();
    }

    @Override
    public Sprint mapUpdateSprintDTOtoSprintDTO(UpdateSprintDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Sprint.SprintBuilder sprint = Sprint.builder();

        sprint.name( dto.getName() );
        sprint.startDate( dto.getStartDate() );
        sprint.createdDate( dto.getCreatedDate() );
        sprint.endDate( dto.getEndDate() );

        return sprint.build();
    }

    @Override
    public List<ListSprintDTO> mapSprintEntitiesToListSprintDTOs(List<Sprint> sprintList) {
        if ( sprintList == null ) {
            return null;
        }

        List<ListSprintDTO> list = new ArrayList<ListSprintDTO>( sprintList.size() );
        for ( Sprint sprint : sprintList ) {
            list.add( mapSprintEntityToSListSprintDTO( sprint ) );
        }

        return list;
    }

    @Override
    public ListSprintDTO mapSprintEntityToSListSprintDTO(Sprint sprint) {
        if ( sprint == null ) {
            return null;
        }

        ListSprintDTO.ListSprintDTOBuilder listSprintDTO = ListSprintDTO.builder();

        listSprintDTO.id( sprint.getId() );
        listSprintDTO.name( sprint.getName() );

        return listSprintDTO.build();
    }

    @Override
    public void copyFields(Sprint updatedSprint, Sprint sprint) {
        if ( updatedSprint == null ) {
            return;
        }

        if ( updatedSprint.getId() != null ) {
            sprint.setId( updatedSprint.getId() );
        }
        if ( updatedSprint.getName() != null ) {
            sprint.setName( updatedSprint.getName() );
        }
        if ( updatedSprint.getStartDate() != null ) {
            sprint.setStartDate( updatedSprint.getStartDate() );
        }
        if ( updatedSprint.getCreatedDate() != null ) {
            sprint.setCreatedDate( updatedSprint.getCreatedDate() );
        }
        if ( updatedSprint.getEndDate() != null ) {
            sprint.setEndDate( updatedSprint.getEndDate() );
        }
        if ( updatedSprint.getSprintCreator() != null ) {
            sprint.setSprintCreator( updatedSprint.getSprintCreator() );
        }
        if ( updatedSprint.getTeam() != null ) {
            sprint.setTeam( updatedSprint.getTeam() );
        }
        if ( updatedSprint.getUpdatedAt() != null ) {
            sprint.setUpdatedAt( updatedSprint.getUpdatedAt() );
        }
        if ( updatedSprint.getUpdatedBy() != null ) {
            sprint.setUpdatedBy( updatedSprint.getUpdatedBy() );
        }
        if ( updatedSprint.getIsDeleted() != null ) {
            sprint.setIsDeleted( updatedSprint.getIsDeleted() );
        }
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

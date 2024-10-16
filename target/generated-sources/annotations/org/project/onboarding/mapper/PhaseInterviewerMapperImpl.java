package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.hiringPhase.HiringPhaseLookUpDTO;
import org.project.onboarding.dto.phaseInterviewer.GetPhaseInterviewerDTO;
import org.project.onboarding.dto.phaseInterviewer.ListPhaseInterviewerDTO;
import org.project.onboarding.dto.phaseInterviewer.PostPhaseInterviewerDTO;
import org.project.onboarding.dto.phaseInterviewer.UpdatePhaseInterviewerDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.HiringPhase;
import org.project.onboarding.entity.PhaseInterviewer;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class PhaseInterviewerMapperImpl implements PhaseInterviewerMapper {

    @Override
    public PhaseInterviewer mapPostPhaseInterviewerDTOtoPhaseInterviewerEntity(PostPhaseInterviewerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PhaseInterviewer.PhaseInterviewerBuilder phaseInterviewer = PhaseInterviewer.builder();

        phaseInterviewer.notes( dto.getNotes() );
        phaseInterviewer.score( dto.getScore() );

        return phaseInterviewer.build();
    }

    @Override
    public GetPhaseInterviewerDTO mapPhaseInterviewerEntityToGetPhaseInterviewerDTO(PhaseInterviewer phaseInterviewer) {
        if ( phaseInterviewer == null ) {
            return null;
        }

        GetPhaseInterviewerDTO.GetPhaseInterviewerDTOBuilder getPhaseInterviewerDTO = GetPhaseInterviewerDTO.builder();

        getPhaseInterviewerDTO.hiringPhase( hiringPhaseToHiringPhaseLookUpDTO( phaseInterviewer.getHiringPhase() ) );
        getPhaseInterviewerDTO.interviewer( employeeToEmployeeDTO( phaseInterviewer.getInterviewer() ) );
        getPhaseInterviewerDTO.notes( phaseInterviewer.getNotes() );
        getPhaseInterviewerDTO.score( phaseInterviewer.getScore() );

        return getPhaseInterviewerDTO.build();
    }

    @Override
    public List<ListPhaseInterviewerDTO> mapPhaseInterviewerEntitiesToListPhaseInterviewerDTOs(List<PhaseInterviewer> phaseInterviewerList) {
        if ( phaseInterviewerList == null ) {
            return null;
        }

        List<ListPhaseInterviewerDTO> list = new ArrayList<ListPhaseInterviewerDTO>( phaseInterviewerList.size() );
        for ( PhaseInterviewer phaseInterviewer : phaseInterviewerList ) {
            list.add( phaseInterviewerToListPhaseInterviewerDTO( phaseInterviewer ) );
        }

        return list;
    }

    @Override
    public void copyFields(PhaseInterviewer updatedPhaseInterviewer, PhaseInterviewer phaseInterviewer) {
        if ( updatedPhaseInterviewer == null ) {
            return;
        }

        if ( updatedPhaseInterviewer.getId() != null ) {
            phaseInterviewer.setId( updatedPhaseInterviewer.getId() );
        }
        if ( updatedPhaseInterviewer.getHiringPhase() != null ) {
            phaseInterviewer.setHiringPhase( updatedPhaseInterviewer.getHiringPhase() );
        }
        if ( updatedPhaseInterviewer.getInterviewer() != null ) {
            phaseInterviewer.setInterviewer( updatedPhaseInterviewer.getInterviewer() );
        }
        if ( updatedPhaseInterviewer.getNotes() != null ) {
            phaseInterviewer.setNotes( updatedPhaseInterviewer.getNotes() );
        }
        if ( updatedPhaseInterviewer.getScore() != null ) {
            phaseInterviewer.setScore( updatedPhaseInterviewer.getScore() );
        }
        if ( updatedPhaseInterviewer.getUpdatedAt() != null ) {
            phaseInterviewer.setUpdatedAt( updatedPhaseInterviewer.getUpdatedAt() );
        }
        if ( updatedPhaseInterviewer.getUpdatedBy() != null ) {
            phaseInterviewer.setUpdatedBy( updatedPhaseInterviewer.getUpdatedBy() );
        }
        if ( updatedPhaseInterviewer.getIsDeleted() != null ) {
            phaseInterviewer.setIsDeleted( updatedPhaseInterviewer.getIsDeleted() );
        }
    }

    @Override
    public PhaseInterviewer mapUpdatePhaseInterviewerDTOtoPhaseInterviewerEntity(UpdatePhaseInterviewerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PhaseInterviewer.PhaseInterviewerBuilder phaseInterviewer = PhaseInterviewer.builder();

        phaseInterviewer.notes( dto.getNotes() );
        phaseInterviewer.score( dto.getScore() );

        return phaseInterviewer.build();
    }

    protected HiringPhaseLookUpDTO hiringPhaseToHiringPhaseLookUpDTO(HiringPhase hiringPhase) {
        if ( hiringPhase == null ) {
            return null;
        }

        HiringPhaseLookUpDTO.HiringPhaseLookUpDTOBuilder<?, ?> hiringPhaseLookUpDTO = HiringPhaseLookUpDTO.builder();

        hiringPhaseLookUpDTO.id( hiringPhase.getId() );
        hiringPhaseLookUpDTO.name( hiringPhase.getName() );

        return hiringPhaseLookUpDTO.build();
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

    protected ListPhaseInterviewerDTO phaseInterviewerToListPhaseInterviewerDTO(PhaseInterviewer phaseInterviewer) {
        if ( phaseInterviewer == null ) {
            return null;
        }

        ListPhaseInterviewerDTO.ListPhaseInterviewerDTOBuilder listPhaseInterviewerDTO = ListPhaseInterviewerDTO.builder();

        listPhaseInterviewerDTO.id( phaseInterviewer.getId() );

        return listPhaseInterviewerDTO.build();
    }
}

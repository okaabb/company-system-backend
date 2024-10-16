package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.training.GetTrainingDTO;
import org.project.onboarding.dto.training.PostTrainingDTO;
import org.project.onboarding.dto.training.TrainingLookUpDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Training;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:27+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class TrainingMapperImpl implements TrainingMapper {

    @Override
    public Training postToEntity(PostTrainingDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Training.TrainingBuilder training = Training.builder();

        training.name( dto.getName() );
        training.createdDate( dto.getCreatedDate() );
        training.trainingHours( dto.getTrainingHours() );

        return training.build();
    }

    @Override
    public GetTrainingDTO entityToGet(Training training) {
        if ( training == null ) {
            return null;
        }

        GetTrainingDTO.GetTrainingDTOBuilder<?, ?> getTrainingDTO = GetTrainingDTO.builder();

        getTrainingDTO.name( training.getName() );
        getTrainingDTO.creator( employeeToEmployeeDTO( training.getCreator() ) );
        getTrainingDTO.createdDate( training.getCreatedDate() );
        getTrainingDTO.trainingHours( training.getTrainingHours() );

        return getTrainingDTO.build();
    }

    @Override
    public List<TrainingLookUpDTO> entitiesToLookUpList(List<Training> all) {
        if ( all == null ) {
            return null;
        }

        List<TrainingLookUpDTO> list = new ArrayList<TrainingLookUpDTO>( all.size() );
        for ( Training training : all ) {
            list.add( trainingToTrainingLookUpDTO( training ) );
        }

        return list;
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

    protected TrainingLookUpDTO trainingToTrainingLookUpDTO(Training training) {
        if ( training == null ) {
            return null;
        }

        TrainingLookUpDTO.TrainingLookUpDTOBuilder<?, ?> trainingLookUpDTO = TrainingLookUpDTO.builder();

        trainingLookUpDTO.id( training.getId() );
        trainingLookUpDTO.name( training.getName() );

        return trainingLookUpDTO.build();
    }
}

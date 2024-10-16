package org.project.onboarding.mapper;

import javax.annotation.processing.Generated;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.employeeTraining.GetEmployeeTrainingDTO;
import org.project.onboarding.dto.training.TrainingLookUpDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.EmployeeTraining;
import org.project.onboarding.entity.Training;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class EmployeeTrainingMapperImpl implements EmployeeTrainingMapper {

    @Override
    public GetEmployeeTrainingDTO entityToGet(EmployeeTraining employeeTraining) {
        if ( employeeTraining == null ) {
            return null;
        }

        GetEmployeeTrainingDTO.GetEmployeeTrainingDTOBuilder getEmployeeTrainingDTO = GetEmployeeTrainingDTO.builder();

        getEmployeeTrainingDTO.id( employeeTraining.getId() );
        getEmployeeTrainingDTO.training( trainingToTrainingLookUpDTO( employeeTraining.getTraining() ) );
        getEmployeeTrainingDTO.employee( employeeToEmployeeDTO( employeeTraining.getEmployee() ) );
        getEmployeeTrainingDTO.enrollmentDate( employeeTraining.getEnrollmentDate() );

        return getEmployeeTrainingDTO.build();
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
}

package org.project.onboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.employeeTraining.GetEmployeeTrainingDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.EmployeeTraining;
import org.project.onboarding.entity.Training;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeTrainingMapper {
    GetEmployeeTrainingDTO entityToGet(EmployeeTraining employeeTraining);

    default Long mapTraining(Training value) {
        return value.getId();
    }
    default Long mapEmployee(Employee value) {
        return value.getId();
    }
}

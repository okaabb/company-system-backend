package org.project.onboarding.interfaces;

import org.project.onboarding.entity.EmployeeTraining;
import org.project.onboarding.repository.EmployeeTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeeTrainingService {

    EmployeeTraining saveEmployeeTraining(EmployeeTraining employeeTraining);

    List<EmployeeTraining> getAllEmployeeTrainings();

    EmployeeTraining getEmployeeTrainingById(Long id);

    EmployeeTraining updateEmployeeTraining(Long id, EmployeeTraining updatedEmployeeTraining);

    void deleteEmployeeTraining(Long id);
}

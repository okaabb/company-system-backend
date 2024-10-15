package org.project.onboarding.service;

import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.dto.employeeTraining.GetEmployeeTrainingDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.EmployeeTraining;
import org.project.onboarding.entity.Training;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.mapper.EmployeeTrainingMapper;
import org.project.onboarding.repository.EmployeeTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Service
public class EmployeeTrainingServiceImpl {
    private final EmployeeTrainingRepository employeeTrainingRepository;
    private final TrainingServiceImpl trainingService;
    private final EmployeeTrainingMapper employeeTrainingMapper;
    private final EmployeeBusinessLayer employeeBusinessLayer;

    @Autowired
    public EmployeeTrainingServiceImpl(EmployeeTrainingRepository employeeTrainingRepository, TrainingServiceImpl trainingService,EmployeeTrainingMapper employeeTrainingMapper, EmployeeBusinessLayer employeeBusinessLayer) {
        this.employeeTrainingRepository = employeeTrainingRepository;
        this.trainingService = trainingService;
        this.employeeTrainingMapper = employeeTrainingMapper;
        this.employeeBusinessLayer = employeeBusinessLayer;
    }

    public GetEmployeeTrainingDTO enrollEmployeeTraining(@PathVariable Long trainingId, @PathVariable Long empId) throws BusinessException {
        EmployeeTraining employeeTraining = new EmployeeTraining();
        Training training = trainingService.validateTraining(trainingId);
        Employee employee = employeeBusinessLayer.getEmployeeEntityById(empId);
        employeeTraining.setEmployee(employee);
        employeeTraining.setTraining(training);
        employeeTraining.setEnrollmentDate(LocalDate.now());
        employeeTrainingRepository.save(employeeTraining);
        return employeeTrainingMapper.entityToGet(employeeTraining);
    }
}

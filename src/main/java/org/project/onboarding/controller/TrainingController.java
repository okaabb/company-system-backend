package org.project.onboarding.controller;

import org.project.onboarding.dto.employeeTraining.GetEmployeeTrainingDTO;
import org.project.onboarding.dto.training.GetTrainingDTO;
import org.project.onboarding.dto.training.PostTrainingDTO;
import org.project.onboarding.dto.training.TrainingLookUpDTO;
import org.project.onboarding.entity.Training;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.service.EmployeeTrainingServiceImpl;
import org.project.onboarding.service.TrainingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainings")
public class TrainingController {
    private final TrainingServiceImpl trainingServiceImpl;
    private final EmployeeTrainingServiceImpl employeeTrainingService;

    @Autowired
    public TrainingController(TrainingServiceImpl trainingServiceImpl, EmployeeTrainingServiceImpl employeeTrainingService) {
        this.trainingServiceImpl = trainingServiceImpl;
        this.employeeTrainingService = employeeTrainingService;
    }

    @PostMapping()
    public ResponseEntity<GetTrainingDTO> saveTraining(@RequestBody PostTrainingDTO training) throws BusinessException {
        return ResponseEntity.ok(trainingServiceImpl.saveTraining(training));
    }

    @GetMapping()
    public ResponseEntity<List<TrainingLookUpDTO>> getAllTrainings() {
        return ResponseEntity.ok(trainingServiceImpl.getAllTrainings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTrainingDTO> getTrainingById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(trainingServiceImpl.getTrainingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetTrainingDTO> updateTraining(@PathVariable Long id, @RequestBody PostTrainingDTO training) throws BusinessException {
        return ResponseEntity.ok(trainingServiceImpl.updateTraining(id, training));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) throws BusinessException {
        trainingServiceImpl.deleteTraining(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/enroll/{empId}")
    public ResponseEntity<GetEmployeeTrainingDTO> enrollEmployeeTraining(@PathVariable Long id, @PathVariable Long empId) throws BusinessException {
        return ResponseEntity.ok(employeeTrainingService.enrollEmployeeTraining(id, empId));
    }
}

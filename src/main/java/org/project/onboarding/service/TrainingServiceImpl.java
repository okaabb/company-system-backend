package org.project.onboarding.service;

import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.dto.training.GetTrainingDTO;
import org.project.onboarding.dto.training.PostTrainingDTO;
import org.project.onboarding.dto.training.TrainingLookUpDTO;
import org.project.onboarding.entity.Training;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.TrainingService;
import org.project.onboarding.mapper.TrainingMapper;
import org.project.onboarding.repository.TrainingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final EmployeeBusinessLayer employeeBusinessLayer;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper, EmployeeBusinessLayer employeeBusinessLayer) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
        this.employeeBusinessLayer = employeeBusinessLayer;
    }

    @Override
    public GetTrainingDTO saveTraining(PostTrainingDTO dto) throws BusinessException {
        Training training = trainingMapper.postToEntity(dto);
        Employee creator = employeeBusinessLayer.getEmployeeEntityById(dto.getCreator());
        training.setCreator(creator);
        trainingRepository.save(training);
        return trainingMapper.entityToGet(training);
    }

    @Override
    public List<TrainingLookUpDTO> getAllTrainings() {
        return trainingMapper.entitiesToLookUpList(trainingRepository.findAll());
    }

    @Override
    public GetTrainingDTO getTrainingById(Long id) throws BusinessException {
        return trainingMapper.entityToGet(validateTraining(id));
    }

    @Override
    public GetTrainingDTO updateTraining(Long id, PostTrainingDTO dto) throws BusinessException {
        Training training = validateTraining(id);
        Training updatedTraining = trainingMapper.postToEntity(dto);
        Employee creator = employeeBusinessLayer.getEmployeeEntityById(dto.getCreator());
        updatedTraining.setCreator(creator);
        training = fillFields(updatedTraining, training);
        trainingRepository.save(training);
        return trainingMapper.entityToGet(training);
    }

    @Override
    public void deleteTraining(Long id) throws BusinessException {
        validateTraining(id);
        trainingRepository.deleteById(id);
    }

    @Override
    public Training fillFields(Training source, Training target) {
        BeanUtils.copyProperties(source, target, "id", "createdAt", "createdBy");
        return target;
    }

    @Override
    public Training validateTraining(Long trainingId) throws BusinessException {
        Optional<Training> training = trainingRepository.findById(trainingId);
        if (training.isEmpty()) throw new BusinessException("Training does not exist.", HttpStatus.NOT_FOUND);
        return training.get();
    }

}

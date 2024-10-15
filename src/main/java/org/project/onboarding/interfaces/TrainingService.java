package org.project.onboarding.interfaces;

import org.project.onboarding.dto.training.GetTrainingDTO;
import org.project.onboarding.dto.training.PostTrainingDTO;
import org.project.onboarding.dto.training.TrainingLookUpDTO;
import org.project.onboarding.entity.Training;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TrainingService {
    GetTrainingDTO saveTraining(PostTrainingDTO training) throws BusinessException;

    List<TrainingLookUpDTO> getAllTrainings();

    GetTrainingDTO getTrainingById(Long id) throws BusinessException;

    GetTrainingDTO updateTraining(Long id, PostTrainingDTO updatedTraining) throws BusinessException;

    Training validateTraining(Long trainingId) throws BusinessException;

    Training fillFields(Training oldTraining, Training newTraining);

    void deleteTraining(Long id) throws BusinessException;
}

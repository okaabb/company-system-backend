package org.project.onboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.training.GetTrainingDTO;
import org.project.onboarding.dto.training.PostTrainingDTO;
import org.project.onboarding.dto.training.TrainingLookUpDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Training;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TrainingMapper {
    @Mapping(target = "creator", ignore = true)
    Training postToEntity(PostTrainingDTO dto);

    GetTrainingDTO entityToGet(Training training);

    List<TrainingLookUpDTO> entitiesToLookUpList(List<Training> all);

    default Long map(Employee value) {
        return value.getId();
    }
}

package org.project.onboarding.businessLayers;

import jakarta.validation.constraints.NotNull;
import org.project.onboarding.entity.Team;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class TeamBusinessLayer {
    private final TeamRepository teamRepository;

    public TeamBusinessLayer(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team getEntityWithId(Long id) throws BusinessException {
        Team team = teamRepository.findByIdAndIsDeletedFalse(id);
        if (isNull(team)) throw new BusinessException("Team does not exist.", HttpStatus.NOT_FOUND);
        return team;
    }
}

package org.project.onboarding.service;


import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.businessLayers.TeamBusinessLayer;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.sprint.ListSprintDTO;
import org.project.onboarding.dto.sprint.GetSprintDTO;
import org.project.onboarding.dto.sprint.ListSprintDTO;
import org.project.onboarding.dto.sprint.PostSprintDTO;
import org.project.onboarding.dto.sprint.UpdateSprintDTO;
import org.project.onboarding.entity.Sprint;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Sprint;
import org.project.onboarding.entity.Team;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.SprintService;
import org.project.onboarding.mapper.SprintMapper;
import org.project.onboarding.predicates.Applicant.ApplicantPredicateBuilder;
import org.project.onboarding.predicates.Sprint.SprintPredicateBuilder;
import org.project.onboarding.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.project.onboarding.helperFunctions.PredicatesHelper.createPageResponse;
import static org.project.onboarding.helperFunctions.PredicatesHelper.isPageableKey;

@Service
public class SprintServiceImpl implements SprintService {
    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;
    private final TeamBusinessLayer teamBusinessLayer;
    private final EmployeeBusinessLayer employeeBusinessLayer;

    @Autowired
    public SprintServiceImpl(SprintRepository sprintRepository, SprintMapper sprintMapper, TeamBusinessLayer teamBusinessLayer, EmployeeBusinessLayer employeeBusinessLayer) {
        this.sprintRepository = sprintRepository;
        this.sprintMapper = sprintMapper;
        this.teamBusinessLayer = teamBusinessLayer;
        this.employeeBusinessLayer = employeeBusinessLayer;
    }

    @Override
    public void saveSprint(PostSprintDTO dto) throws BusinessException {
        Sprint sprint = sprintMapper.mapPostSprintDTOtoSprintDTO(dto);
        validateEnteredTimeDates(sprint);
        Team team = teamBusinessLayer.getEntityWithId(dto.getTeamId());
        Employee creator = employeeBusinessLayer.getEmployeeEntityById(dto.getCreator());
        sprint.setTeam(team);
        sprint.setSprintCreator(creator);
        sprintRepository.save(sprint);
    }

    private void validateEnteredTimeDates(Sprint sprint) throws BusinessException {
        LocalDate creationDate = sprint.getCreatedDate();
        LocalDate startDate = sprint.getStartDate();
        LocalDate endDate = sprint.getEndDate();
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate))
            throw new BusinessException("Start data can not be same/after end date.", HttpStatus.BAD_REQUEST);
        if (creationDate.isAfter(LocalDate.now()) || creationDate.isEqual(LocalDate.now()))
            throw new BusinessException("Start data can not be same/after current date.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public PaginationDTO<ListSprintDTO> getAllSprints(Map<String, String> search, Pageable pageable) {
        SprintPredicateBuilder builder = new SprintPredicateBuilder();
        extractQueryParameters(builder, search);

        Page<Sprint> sprints = sprintRepository.findAll(builder.build(), pageable);

        List<Sprint> sprintList = sprints.getContent();
        List<ListSprintDTO> content = sprintMapper.mapSprintEntitiesToListSprintDTOs(sprintList);
        return createPageResponse(content, pageable, sprints.getTotalElements(), sprints.getTotalPages());

    }

    @Override
    public GetSprintDTO getSprintById(Long id) throws BusinessException {
        Sprint sprint = getEntityWithId(id);
        return sprintMapper.mapSprintEntityToGetSprintDTO(sprint);
    }

    @Override
    public void updateSprint(Long id, UpdateSprintDTO dto) throws BusinessException {
        Sprint sprint = getEntityWithId(id);
        Sprint updatedSprint = sprintMapper.mapUpdateSprintDTOtoSprintDTO(dto);

        validateEnteredTimeDates(updatedSprint);

        Team team = teamBusinessLayer.getEntityWithId(dto.getTeamId());
        Employee creator = employeeBusinessLayer.getEmployeeEntityById(dto.getCreator());
        updatedSprint.setTeam(team);
        updatedSprint.setSprintCreator(creator);

        sprintMapper.copyFields(updatedSprint, sprint);
        sprintRepository.save(sprint);
    }

    @Override
    public void deleteSprint(Long id) throws BusinessException {
        Sprint sprint = getEntityWithId(id);
        sprint.setIsDeleted(true);
        sprintRepository.save(sprint);
    }


    @Override
    public Sprint getEntityWithId(Long sprintId) throws BusinessException {
        Optional<Sprint> sprint = sprintRepository.findById(sprintId);
        if (sprint.isEmpty()) throw new BusinessException("Sprint does not exist.", HttpStatus.NOT_FOUND);
        return sprint.get();
    }

    @Override
    public GetSprintDTO assignSprintToTeam(Long id, Long teamId) throws BusinessException {
        Team team = teamBusinessLayer.getEntityWithId(teamId);
        Sprint sprint = getEntityWithId(id);
        if (!isNull(sprint.getTeam()))
            throw new BusinessException("This Sprint is already assigned to another Team", HttpStatus.BAD_REQUEST);
        sprint.setTeam(team);
        sprintRepository.save(sprint);
        return sprintMapper.mapSprintEntityToGetSprintDTO(sprint);
    }

    public static void extractQueryParameters(SprintPredicateBuilder builder, Map<String, String> search) {
        if (search != null) {
            for (Map.Entry<String, String> entry : search.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (isPageableKey(key)) continue;
                builder.with(key, "=", value);
            }
        }
        builder.with("isDeleted", "=", "false");
    }

}

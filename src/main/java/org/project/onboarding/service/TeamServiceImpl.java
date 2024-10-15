package org.project.onboarding.service;

import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.task.ListTaskDTO;
import org.project.onboarding.dto.team.GetTeamResponseDTO;
import org.project.onboarding.dto.team.ListTeamDTO;
import org.project.onboarding.dto.team.PostTeamRequestDTO;
import org.project.onboarding.dto.team.UpdateTeamDTO;
import org.project.onboarding.dto.teamMember.AssignTeamMemberDTO;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.QTeam;
import org.project.onboarding.entity.Team;
import org.project.onboarding.enums.PositionEnum;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.DepartmentService;
import org.project.onboarding.interfaces.TeamService;
import org.project.onboarding.mapper.TeamMapper;
import org.project.onboarding.predicates.Team.TeamPredicateBuilder;
import org.project.onboarding.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.project.onboarding.helperFunctions.PredicatesHelper.createPageResponse;
import static org.project.onboarding.helperFunctions.PredicatesHelper.isPageableKey;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final DepartmentService departmentService;
    private final EmployeeBusinessLayer employeeBusinessLayer;
    private final TeamMapper teamMapper;
    private final TeamMemberServiceImpl teamMemberService;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, DepartmentService departmentService, TeamMapper teamMapper, TeamMemberServiceImpl teamMemberService, EmployeeBusinessLayer employeeBusinessLayer) {
        this.teamRepository = teamRepository;
        this.departmentService = departmentService;
        this.employeeBusinessLayer = employeeBusinessLayer;
        this.teamMapper = teamMapper;
        this.teamMemberService = teamMemberService;
    }

    @Override
    public void saveTeam(PostTeamRequestDTO teamDTO) throws BusinessException {
        Team team = teamMapper.mapPostTeamDTOtoTeamEntity(teamDTO);

        Long teamLeadId = teamDTO.getTeamLeadId();
        if (!isNull(teamLeadId)) {
            Employee teamLead = validateTeamLeadExistence(teamLeadId);
            team.setTeamLead(teamLead);
        }

        String departmentName = teamDTO.getDepartment();
        Department department = departmentService.getDepartmentWithName(departmentName);
        team.setDepartment(department);

        String teamName = teamDTO.getName();
        checkNameUniquenessOnSave(teamName);

        teamRepository.save(team);
    }

    @Override
    public PaginationDTO<ListTeamDTO> getAllTeams(Map<String, String> search, Pageable pageable) {
        TeamPredicateBuilder builder = new TeamPredicateBuilder();
        extractQueryParameters(builder, search);

        Page<Team> teams = teamRepository.findAll(builder.build(), pageable);

        List<Team> teamList = teams.getContent();
        List<ListTeamDTO> content = teamMapper.mapTeamEntitiesToListTeamDTOs(teamList);
        return createPageResponse(content, pageable, teams.getTotalElements(), teams.getTotalPages());

    }

    public static void extractQueryParameters(TeamPredicateBuilder builder, Map<String, String> search) {
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

    @Override
    public GetTeamResponseDTO getTeamById(Long id) throws BusinessException {
        Team team = getTeamEntityById(id);
        return teamMapper.mapTeamEntityToGetTeamDTO(team);
    }

    @Override
    public void updateTeam(Long id, UpdateTeamDTO teamDTO) throws BusinessException {
        Team team = getTeamEntityById(id);

        Long teamLeadId = teamDTO.getTeamLeadId();
        if (!isNull(teamLeadId)) {
            Employee teamLead = validateTeamLeadExistence(teamLeadId);
            team.setTeamLead(teamLead);
        }

        String departmentName = teamDTO.getDepartment();
        if (!isNull(departmentName)) {
            Department department = departmentService.getDepartmentWithName(departmentName);
            team.setDepartment(department);
        }

        String teamName = teamDTO.getName();
        if (!isNull(teamName)) {
            checkNameUniquenessOnUpdate(teamName, id);
            team.setName(teamName);
        }
        teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long id) throws BusinessException {
        Team team = getTeamEntityById(id);
        team.setIsDeleted(true);
        teamRepository.save(team);
    }

    public Team getTeamEntityById(Long teamId) throws BusinessException {
        Team team = teamRepository.findByIdAndIsDeletedFalse(teamId);
        if (isNull(team))
            throw new BusinessException("Team does not exist.", HttpStatus.NOT_FOUND);
        return team;
    }

    @Override
    public void assignTeamLead(Long teamId, Long teamLeadId) throws BusinessException {
        Team team = getTeamEntityById(teamId);
        Employee teamLead = employeeBusinessLayer.getEmployeeEntityById(teamLeadId);
        team.setTeamLead(teamLead);
        teamRepository.save(team);
    }

    @Override
    public void assignTeamMember(Long id, Long memberId, AssignTeamMemberDTO teamMemberDTO) throws BusinessException {
        teamMemberService.saveTeamMember(id, memberId, teamMemberDTO);
    }

    private Employee validateTeamLeadExistence(Long teamLeadId) throws BusinessException {
        Employee teamLead = employeeBusinessLayer.getEmployeeEntityById(teamLeadId);
        if (teamLead.getPosition() != PositionEnum.TEAM_LEAD)
            throw new BusinessException("Employee with given id is not a team lead.", HttpStatus.BAD_REQUEST);
        return teamLead;
    }

    public void checkNameUniquenessOnSave(String teamName) throws BusinessException {
        Team teamWithEqName = teamRepository.findByNameAndIsDeletedFalse(teamName);
        if (!isNull(teamWithEqName))
            throw new BusinessException("A team with name already exists.", HttpStatus.BAD_REQUEST);
    }

    public void checkNameUniquenessOnUpdate(String teamName, Long id) throws BusinessException {
        Team teamWithEqName = teamRepository.findByNameAndIsDeletedFalseAndIdIsNot(teamName, id);
        if (!isNull(teamWithEqName))
            throw new BusinessException("A team with name already exists.", HttpStatus.BAD_REQUEST);
    }

}

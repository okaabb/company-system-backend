package org.project.onboarding.service;

import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.businessLayers.TeamBusinessLayer;
import org.project.onboarding.dto.teamMember.AssignTeamMemberDTO;
import org.project.onboarding.dto.teamMember.GetTeamMemberDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Team;
import org.project.onboarding.entity.TeamMember;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.TeamMemberService;
import org.project.onboarding.mapper.TeamMemberMapper;
import org.project.onboarding.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

@Service

public class TeamMemberServiceImpl implements TeamMemberService {
    private final EmployeeBusinessLayer employeeBusinessLayer;
    private final TeamBusinessLayer teamBusinessLayer;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamMemberMapper teamMemberMapper;

    public TeamMemberServiceImpl(EmployeeBusinessLayer employeeBusinessLayer, TeamBusinessLayer teamBusinessLayer, TeamMemberRepository teamMemberRepository, TeamMemberMapper teamMemberMapper) {
        this.employeeBusinessLayer = employeeBusinessLayer;
        this.teamBusinessLayer = teamBusinessLayer;
        this.teamMemberRepository = teamMemberRepository;
        this.teamMemberMapper = teamMemberMapper;
    }

    @Override
    public GetTeamMemberDTO saveTeamMember(Long teamId, Long memberId, AssignTeamMemberDTO dto) throws BusinessException {
        Employee employee = employeeBusinessLayer.getEmployeeEntityById(memberId);
        Team team = teamBusinessLayer.getEntityWithId(teamId);
        TeamMember teamMember = new TeamMember();
        teamMember.setTeam(team);
        teamMember.setRole(dto.getRole());
        teamMember.setEmployee(employee);
        teamMemberRepository.save(teamMember);
        return teamMemberMapper.entityToGet(teamMember);
    }
}

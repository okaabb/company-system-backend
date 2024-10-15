package org.project.onboarding.interfaces;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.task.ListTaskDTO;
import org.project.onboarding.dto.team.GetTeamResponseDTO;
import org.project.onboarding.dto.team.ListTeamDTO;
import org.project.onboarding.dto.team.PostTeamRequestDTO;
import org.project.onboarding.dto.team.UpdateTeamDTO;
import org.project.onboarding.dto.teamMember.AssignTeamMemberDTO;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TeamService {

    void saveTeam(PostTeamRequestDTO dto) throws BusinessException;

    PaginationDTO<ListTeamDTO> getAllTeams(Map<String, String> search, Pageable pageable);

    GetTeamResponseDTO getTeamById(Long id) throws BusinessException;

    void updateTeam(Long id, UpdateTeamDTO dto) throws BusinessException;

    void deleteTeam(Long id) throws BusinessException;

    void assignTeamLead(Long teamId, Long teamLeadId) throws BusinessException;

    void assignTeamMember(Long id, Long memberId, AssignTeamMemberDTO dto) throws BusinessException;
}

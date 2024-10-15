package org.project.onboarding.controller;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.task.ListTaskDTO;
import org.project.onboarding.dto.team.GetTeamResponseDTO;
import org.project.onboarding.dto.team.ListTeamDTO;
import org.project.onboarding.dto.team.PostTeamRequestDTO;
import org.project.onboarding.dto.team.UpdateTeamDTO;
import org.project.onboarding.dto.teamMember.AssignTeamMemberDTO;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Void> saveTeam(@RequestBody PostTeamRequestDTO team) throws BusinessException {
        teamService.saveTeam(team);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginationDTO<ListTeamDTO>> getAllTeams(Map<String, String> search, Pageable pageable) {
        return ResponseEntity.ok(teamService.getAllTeams(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTeamResponseDTO> getTeamById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetTeamResponseDTO> updateTeam(@PathVariable Long id, @RequestBody UpdateTeamDTO team) throws BusinessException {
        teamService.updateTeam(id, team);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) throws BusinessException {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/assign-lead/{teamLeadId}")
    public ResponseEntity<Void> assignTeamLead(@PathVariable Long id, @PathVariable Long teamLeadId) throws BusinessException {
        teamService.assignTeamLead(id, teamLeadId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/assign-members/{memberId}")
    public ResponseEntity<Void> assignTeamMember(@PathVariable Long id, @PathVariable Long memberId, @RequestBody AssignTeamMemberDTO dto) throws BusinessException {
        teamService.assignTeamMember(id, memberId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

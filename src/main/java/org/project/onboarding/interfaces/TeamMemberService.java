package org.project.onboarding.interfaces;

import org.project.onboarding.dto.teamMember.AssignTeamMemberDTO;
import org.project.onboarding.dto.teamMember.GetTeamMemberDTO;
import org.project.onboarding.exception.BusinessException;

public interface TeamMemberService {
    GetTeamMemberDTO saveTeamMember(Long teamId, Long memberId, AssignTeamMemberDTO dto) throws BusinessException;
}

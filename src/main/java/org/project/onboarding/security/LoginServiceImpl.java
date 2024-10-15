package org.project.onboarding.security;

import lombok.RequiredArgsConstructor;
import org.project.onboarding.dto.login.LoginDTO;
import org.project.onboarding.interfaces.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        return verify(loginDTO);
    }

    private LoginResponse verify(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        var employee = (EmployeePrinciple) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(employee);
        return LoginResponse.builder().token(jwtToken).build();
    }
}

package org.project.onboarding.security;


import lombok.RequiredArgsConstructor;
import org.project.onboarding.dto.login.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    public final LoginServiceImpl loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginEmployee(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(loginService.loginEmployee(loginDTO));
    }

}

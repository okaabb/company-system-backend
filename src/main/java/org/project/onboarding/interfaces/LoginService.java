package org.project.onboarding.interfaces;

import org.project.onboarding.dto.login.LoginDTO;
import org.project.onboarding.security.LoginResponse;

public interface LoginService {
    LoginResponse loginEmployee(LoginDTO employee);
}

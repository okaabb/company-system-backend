package org.project.onboarding.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public class BusinessException extends Exception {
    private String message;
    private HttpStatus httpStatus;
}

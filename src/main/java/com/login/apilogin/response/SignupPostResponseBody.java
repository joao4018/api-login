package com.login.apilogin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author joaocarlos
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SignupPostResponseBody implements GenericResponse{
    private String email;
    private LocalDateTime singUpDate;
    private LocalDateTime lastLogin;
    private LocalDateTime currentLogin;
    private LocalDateTime accountValidate;
    private String status;
}

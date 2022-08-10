package com.login.apilogin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author joaocarlos
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data

public class SignupPostResponseBody extends GenericResponse {
    private String email;
    private LocalDateTime singUpDate;
    private LocalDateTime lastLogin;
    private LocalDateTime currentLogin;
    private LocalDateTime accountValidate;
    private String status;
}

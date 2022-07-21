package com.login.apilogin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author joaocarlos
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SignupPostResponseBody {
    private String email;
    private LocalDateTime singUpDate;
    private LocalDateTime lastLogin;
    private LocalDateTime currentLogin;
    private LocalDateTime accountValidate;
}

package com.login.apilogin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AccessPostRequestBody {

    @NotEmpty(message = "The username cannot be empty")
    @Schema(description = "This is the nickname to signup ", example = "fulano")
    private String userName;

    @NotEmpty(message = "The password cannot be empty")
    @Schema(description = "This is the password to signup ", example = "12345")
    private String password;

    @NotEmpty(message = "The email cannot be empty")
    @Schema(description = "This is the email to signup ", example = "fulano@gmail.com")
    private String email;

    @NotEmpty(message = "The role cannot be empty")
    @Schema(description = "This is the role to signup ", example = "ROLE_USER")
    private String role;
}

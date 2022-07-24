package com.login.apilogin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AccessRecoveryPostRequestBody {

    @NotEmpty(message = "The code cannot be empty")
    @Schema(description = "This is the code to recovery ", example = "12345")
    private String code;

    @NotEmpty(message = "The email cannot be empty")
    @Schema(description = "This is the email to recovery ", example = "fulano@gmail.com")
    private String email;


    @NotEmpty(message = "The password cannot be empty")
    @Schema(description = "This is the password to recovery ", example = "123321")
    private String password;

}

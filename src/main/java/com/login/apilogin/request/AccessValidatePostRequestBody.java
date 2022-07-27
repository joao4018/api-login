package com.login.apilogin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AccessValidatePostRequestBody {

    @NotEmpty(message = "The code cannot be empty")
    @Schema(description = "This is the code of the access code")
    private String code;

}

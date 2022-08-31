package com.login.apilogin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccessCodePostRequestBody {

    @NotEmpty(message = "The email cannot be empty")
    @Schema(description = "This is the email of the access code", example = "fulano@mail.com")
    private String email;

}

package com.login.apilogin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessCodeDto {

    @NotEmpty(message = "The code cannot be empty")
    @Schema(description = "This is the code of the access code", example = "Ba3c56")
    private String code;

    @NotEmpty(message = "The expiryDate cannot be empty")
    @Schema(description = "This is the email of the access code", example = "2022-10-20 14:22:34.808445")
    private LocalDateTime expiryDate;

    @NotEmpty(message = "The registryDate cannot be empty")
    @Schema(description = "This is the email of the access code", example = "2022-10-22 17:25:44.418322")
    private LocalDateTime registryDate;
}

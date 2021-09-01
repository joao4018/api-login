package com.login.apilogin.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.login.apilogin.converter.CpfCnpjConverter;
import com.login.apilogin.enums.CpfCnpjEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PersonalDataPostRequestBody {

    @NotEmpty(message = "The user firstName cannot be empty")
    @Schema(description = "This is the nickname to signup ", example = "00000000000")
    private String cpfCpnj;

    @NotNull
    private CpfCnpjEnum identityTipe;

    @NotEmpty(message = "The user firstName cannot be empty")
    @Schema(description = "This is the nickname to signup ", example = "fulano")
    private String firstName;

    @NotEmpty(message = "The lastName cannot be empty")
    @Schema(description = "This is the nickname to signup ", example = "fulano")
    private String lastName;

    @NotNull
    @Schema(description = "This is the nickname to signup ", example = "1984-05-12")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date bithDate;

}

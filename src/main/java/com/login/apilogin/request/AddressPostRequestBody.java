package com.login.apilogin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class AddressPostRequestBody {
    @NotEmpty(message = "The street name cannot be empty or null")
    @Schema(description = "This is the name of some street ", example = "baker street 221b")
    private String street;
}

package com.login.apilogin.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class AddressRequestBody {
    @NotNull(message = "The street name cannot be null")
    @NotEmpty(message = "The street name cannot be empty")
    private String street;
}

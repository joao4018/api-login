package com.login.apilogin.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class AddressRequestBody {
    @NotEmpty(message = "The street name cannot be empty or null")
    private String street;
}

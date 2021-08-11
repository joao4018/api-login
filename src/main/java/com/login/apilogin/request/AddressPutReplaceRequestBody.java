package com.login.apilogin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class AddressPutReplaceRequestBody {
    @Schema(description = "This is the id of some street to change ", example = "1")
    private Long id;
    @Schema(description = "This is the name of some street ", example = "baker street 221b")
    private String street;
}

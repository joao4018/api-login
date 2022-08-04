package com.login.apilogin.controller;

import com.login.apilogin.response.GenericResponse;
import com.login.apilogin.response.ResponseBody;

public abstract class AbstractController {
    ResponseBody buildResponsyBody(GenericResponse genericResponse, String details, String title) {
        return new ResponseBody()
                .toBuilder()
                .message("Operação realizada com sucesso!")
                .data(genericResponse)
                .status(200)
                .details(details)
                .title(title)
                .build();
    }
}

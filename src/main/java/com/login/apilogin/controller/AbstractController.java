package com.login.apilogin.controller;

import com.login.apilogin.response.GenericResponse;
import com.login.apilogin.response.ResponseBody;
import org.springframework.http.HttpStatus;

public abstract class AbstractController {
    ResponseBody buildResponsyBody(GenericResponse genericResponse, String details, String title) {
        return new ResponseBody()
                .toBuilder()
                .message("Operação realizada com sucesso!")
                .data(genericResponse)
                .status(HttpStatus.OK.value())
                .details(details)
                .title(title)
                .build();
    }
}

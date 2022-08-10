package com.login.apilogin.controller;

import com.login.apilogin.response.GenericResponse;
import com.login.apilogin.response.ResponseBody;
import com.login.apilogin.token.token.converter.TokenConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractController {

    public ResponseBody buildResponsyBody(GenericResponse genericResponse, String details, String title) {
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

package com.login.apilogin.controller;

import com.login.apilogin.exception.BadRequestException;
import com.login.apilogin.response.GenericResponse;
import com.login.apilogin.response.GenericResponseAdapters.GenericResponse;
import com.login.apilogin.response.ResponseBody;
import com.login.apilogin.token.token.converter.TokenConverter;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;

import java.text.ParseException;

@RequiredArgsConstructor
public abstract class AbstractController {
    private final TokenConverter tokenConverter;

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


    private String removeBearer(String authorization) {
        return authorization.replace(
                        tokenConverter
                                .getJwtConfiguration()
                                .getHeader()
                                .getPrefix(), "")
                .trim();
    }

    private SignedJWT decryptValidating(String encryptedToken) throws ParseException {
        String signedToken = tokenConverter.decryptToken(encryptedToken);
        tokenConverter.validateTokenSignature(signedToken);
        return SignedJWT.parse(signedToken);
    }


    protected String getUsername(String authorization) {
        String username;
        try {
            username = decryptValidating(
                    removeBearer(authorization)).getPayload().toJSONObject().get("sub").toString();
        } catch (Exception e) {
            throw new BadRequestException("Token inválido!");
        }
        return username;
    }
}

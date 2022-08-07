package com.login.apilogin.response;

import com.login.apilogin.exception.BadRequestException;
import com.login.apilogin.token.token.converter.TokenConverter;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * @author joaocarlos
 */
@Service
@Slf4j
public class BuilderResponse {
    private final TokenConverter tokenConverter;

    public BuilderResponse(TokenConverter tokenConverter) {
        this.tokenConverter = tokenConverter;
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

    @Cacheable("token")
    public String getUsername(String authorization) {
        log.info("caiu");
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

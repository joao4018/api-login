package com.login.apilogin.config.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.apilogin.config.jwt.JwtConfiguration;
import com.login.apilogin.domain.impl.AccessUser;
import com.login.apilogin.response.LoginPostResponseBody;
import com.login.apilogin.response.ResponseBody;
import com.login.apilogin.token.token.creator.TokenCreator;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.emptyList;

/**
 * @author Joao4018
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfiguration jwtConfiguration;
    private final TokenCreator tokenCreator;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.info("Attempting authentication. . .");
        AccessUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), AccessUser.class);

        if (applicationUser == null) throw new UsernameNotFoundException("Unable to retrieve the username or password");

        log.info("Creating the authentication object for the user '{}' and calling UserDetailServiceImpl loadUserByUsername", applicationUser.getUsername());

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());

        usernamePasswordAuthenticationToken.setDetails(applicationUser);

        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    @SneakyThrows
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        log.info("Authentication was successful for the user '{}', generating JWE token", auth.getName());

        SignedJWT signedJWT = tokenCreator.createSignedJWT(auth);

        String encryptedToken = tokenCreator.encryptToken(signedJWT);

        log.info("Token generated successfully, adding it to the response header");


        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString();
        String teste = mapper.writeValueAsString(ResponseBody
                .builder()
                .data(LoginPostResponseBody
                        .builder()
                        .token(encryptedToken)
                        .build())
                .build());

        response.addHeader("Access-Control-Expose-Headers", "XSRF-TOKEN, " + jwtConfiguration.getHeader().getName());
        response.getWriter().write(teste);       // Write response body.
        response.addHeader(jwtConfiguration.getHeader().getName(), jwtConfiguration.getHeader().getPrefix() + encryptedToken);
    }

}
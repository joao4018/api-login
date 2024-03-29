package com.login.apilogin.token.config;

import com.login.apilogin.config.jwt.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * @author joao4018
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    protected final JwtConfiguration jwtConfiguration;
    static final String SIGN_UP_URL = "/signup**";
    static final String AUTH_URL = "/login**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,SIGN_UP_URL).permitAll()
                .antMatchers(jwtConfiguration.getLoginUrl()).permitAll()
                .antMatchers(HttpMethod.POST,AUTH_URL).permitAll()
//                .antMatchers(HttpMethod.POST,"/registerPersonalData**").permitAll()
                .antMatchers(HttpMethod.GET,"/user**").permitAll()
                .antMatchers(HttpMethod.POST, "/generateAccessCode**").permitAll()
                .antMatchers(HttpMethod.POST, "/recoveryPassword**").permitAll()
                .antMatchers(HttpMethod.POST, "/validateAccessCode**").permitAll()
                .antMatchers(HttpMethod.GET, "/validateUserAccount**").permitAll()
                .antMatchers("/swagger-ui/**/*").permitAll()
                .antMatchers("/v3/api-docs/**/*").permitAll()
                .antMatchers("/v3/api-docs").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/api/v1**").permitAll()
                .anyRequest()
                .authenticated();
    }


}
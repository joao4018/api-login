package com.login.apilogin.controller;

import com.login.apilogin.request.AccessLoginPostRequestBody;
import com.login.apilogin.request.AccessPostRequestBody;
import com.login.apilogin.request.AccessRecoveryPostRequestBody;
import com.login.apilogin.request.PersonalDataPostRequestBody;
import com.login.apilogin.response.ValidateUser;
import com.login.apilogin.response.ResponseBody;
import com.login.apilogin.response.ValidateTokenResponseBody;
import com.login.apilogin.service.impl.AccessUserServiceImpl;
import com.login.apilogin.token.token.converter.TokenConverter;
import com.login.apilogin.util.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import java.text.ParseException;

import static com.login.apilogin.constants.SystemConstantsExceptions.IMPLEMENTED_BY_SPRING_SECURITY_FILTERS;

/**
 * @author joaocarlos
 */
@RestController
@Log4j2
@SecurityRequirement(name = "Authorization")
public class AccessUserController extends AbstractController {

    private final DateUtil dateUtil;
    private final AccessUserServiceImpl accessUserService;
    private final ValidateUser validateUser;

    public AccessUserController(TokenConverter tokenConverter,
                                DateUtil dateUtil,
                                AccessUserServiceImpl accessUserService, ValidateUser validateUser) {
        this.dateUtil = dateUtil;
        this.accessUserService = accessUserService;
        this.validateUser = validateUser;
    }


    @PostMapping("/login")
    @Operation(summary = "Generate Token")
    public void Login(@RequestBody @Valid AccessLoginPostRequestBody accessLoginPostRequestBody) {
        throw new IllegalStateException(IMPLEMENTED_BY_SPRING_SECURITY_FILTERS);
    }

    @PostMapping(path = "/signup")
    @Operation(summary = "Create a new user")
    public ResponseEntity<ResponseBody> save(@RequestBody @Valid AccessPostRequestBody accessRequestBody) {
        return new ResponseEntity<>(buildResponsyBody(accessUserService.createUser(accessRequestBody), "Operação de cadastro", "Create a new user"), HttpStatus.CREATED);
    }

    @PostMapping(path = "/recoveryPassword")
    @Operation(summary = "Recovery a user")
    public ResponseEntity<ResponseBody> recovery(@RequestBody @Valid AccessRecoveryPostRequestBody accessRequestBody) {
        return new ResponseEntity<>(buildResponsyBody(accessUserService.recoveryPassword(accessRequestBody), "Operação de recuperação de usuário", "Recovery a user"), HttpStatus.CREATED);
    }

    @PostMapping(path = "/registerPersonalData")
    @Operation(summary = "register data an user")
    public ResponseEntity<ResponseBody> registerPersonalData(
            @RequestHeader(name = "Authorization", required = false) String authorization,
            @RequestBody @Valid PersonalDataPostRequestBody personalDataPostRequestBody) throws ParseException {
        return new ResponseEntity<>(
                buildResponsyBody(
                        accessUserService.addPersonalDataAtUser(
                                personalDataPostRequestBody,  validateUser.getUsername(authorization)),
                        "Operação de cadastro de dados pessoais",
                        "register data an user"), HttpStatus.CREATED);
    }

    @GetMapping(path = "/user")
    @Operation(summary = "Returns an existing user")
    public ResponseEntity<ResponseBody> getUser(@RequestParam @Valid @NotEmpty(message = "The email cannot be empty") String email) {
        return new ResponseEntity<>(buildResponsyBody(accessUserService.searchUserByEmail(email), "Operação de busca de usuário", "Returns an existing user"), HttpStatus.OK);
    }

    @GetMapping(path = "/validateUserAccount")
    @Operation(summary = "validate an existing user")
    public ResponseEntity<HttpStatus> validateUserAccount(@RequestParam String email) {
        accessUserService.validateUserAccount(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/validateToken")
    @Operation(summary = "validate a token")
    public ResponseEntity<ResponseBody> registerPersonalData(
            @RequestHeader(name = "Authorization", required = false) String authorization) {
        return new ResponseEntity<>(
                buildResponsyBody(
                        new ValidateTokenResponseBody(validateUser.getUsername(authorization)),
                        "Operação de validação de token",
                        "validate a token"), HttpStatus.OK);
    }


}

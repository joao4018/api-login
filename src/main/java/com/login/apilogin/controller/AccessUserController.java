package com.login.apilogin.controller;

import com.login.apilogin.domain.impl.AccessUser;
import com.login.apilogin.request.AccessLoginPostRequestBody;
import com.login.apilogin.request.AccessPostRequestBody;
import com.login.apilogin.request.PersonalDataPostRequestBody;
import com.login.apilogin.service.impl.AccessUserServiceImpl;
import com.login.apilogin.util.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.login.apilogin.constants.SystemConstantsExceptions.IMPLEMENTED_BY_SPRING_SECURITY_FILTERS;

@RestController
@Log4j2
@RequiredArgsConstructor
public class AccessUserController {

    private final DateUtil dateUtil;
    private final AccessUserServiceImpl accessUserService;

    @PostMapping("/login")
    @Operation(summary = "Generate Token")
    public void Login(@RequestBody @Valid AccessLoginPostRequestBody accessLoginPostRequestBody) {
        throw new IllegalStateException(IMPLEMENTED_BY_SPRING_SECURITY_FILTERS);
    }

    @PostMapping(path = "/signup")
    @Operation(summary = "Create a new user")
    public ResponseEntity<AccessUser> save(@RequestBody @Valid AccessPostRequestBody accessRequestBody) {
        return new ResponseEntity<>(accessUserService.createUser(accessRequestBody), HttpStatus.CREATED);
    }

    @PostMapping(path = "/registerPersonalData")
    @Operation(summary = "Create a new user")
    public ResponseEntity<AccessUser> registerPersonalData(
            @RequestBody @Valid PersonalDataPostRequestBody personalDataPostRequestBody,
            @RequestParam String username ) {
        return new ResponseEntity<>(accessUserService
                .addPersonalDataAtUser(personalDataPostRequestBody, username), HttpStatus.CREATED);
    }

    @GetMapping(path = "/user")
    @Operation(summary = "Returns an existing user")
    public ResponseEntity<AccessUser> getUser(
        @RequestParam String email ) {
       return new ResponseEntity<>(accessUserService.searchUserByEmail(email), HttpStatus.OK);
    }

}

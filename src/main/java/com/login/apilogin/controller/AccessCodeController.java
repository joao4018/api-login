package com.login.apilogin.controller;

import com.login.apilogin.request.AccessCodePostRequestBody;
import com.login.apilogin.service.impl.AccessCodeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccessCodeController {

    private final AccessCodeServiceImpl accessCodeService;

    @PostMapping(path = "/generateAccessCode")
    @Operation(summary = "Create a new access code")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid AccessCodePostRequestBody accessCodeRequestBody) {
        accessCodeService.createAccessCode(accessCodeRequestBody);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

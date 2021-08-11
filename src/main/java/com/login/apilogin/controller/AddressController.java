package com.login.apilogin.controller;

import com.login.apilogin.domain.Address;
import com.login.apilogin.request.AddressPostRequestBody;
import com.login.apilogin.request.AddressPutReplaceRequestBody;
import com.login.apilogin.service.impl.AddressServiceImpl;
import com.login.apilogin.util.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("addresses")
@Log4j2
@RequiredArgsConstructor
public class AddressController {

    private final DateUtil dateUtil;
    private final AddressServiceImpl enderecoService;

    @GetMapping(path = "/list")
    @Operation(summary = "List all streets paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "The Street does not exist in the database")
    })
    public ResponseEntity<Page<Address>> list(@ParameterObject Pageable pageable) {
//        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(enderecoService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "List all streets by id")
    public ResponseEntity<Address> findById(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.findById(id));
    }

    @GetMapping(path = "/byStreet")
    @Operation(summary = "List all streets by the name")
    public ResponseEntity<List<Address>> findByIdStreet(@RequestParam String street) {
        return ResponseEntity.ok(enderecoService.findByStreet(street));
    }

    @PostMapping(path = "/saveEndereco")
    @Operation(summary = "Save a new street")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Address> save(@RequestBody @Valid AddressPostRequestBody addressRequestBody) {
        return new ResponseEntity<>(enderecoService.save(addressRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Change a street saved")
    @PutMapping(path = "/replaceEndereco")
    public ResponseEntity<Void> replace(@RequestBody AddressPutReplaceRequestBody addressReplaceRequestBody) {
        enderecoService.replace(addressReplaceRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

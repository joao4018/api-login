package com.login.apilogin.controller;

import com.login.apilogin.domain.Address;
import com.login.apilogin.request.AddressRequestBody;
import com.login.apilogin.service.impl.AddressServiceImpl;
import com.login.apilogin.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("addresses")
@Log4j2
@RequiredArgsConstructor
public class AddressController {

    private final DateUtil dateUtil;
    private final AddressServiceImpl enderecoService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Address>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(enderecoService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.findById(id));
    }

    @GetMapping(path = "/byStreet")
    public ResponseEntity<List<Address>> findByIdStreet(@RequestParam String street){
        return ResponseEntity.ok(enderecoService.findByStreet(street));
    }

    @PostMapping(path = "/saveEndereco")
    public ResponseEntity<Address> save(@RequestBody AddressRequestBody addressRequestBody){
        return new ResponseEntity<>(enderecoService.save(addressRequestBody), HttpStatus.CREATED);
    }

    @PutMapping(path = "/replaceEndereco")
    public ResponseEntity<Void> replace(@RequestBody AddressRequestBody addressRequestBody){
        enderecoService.replace(addressRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

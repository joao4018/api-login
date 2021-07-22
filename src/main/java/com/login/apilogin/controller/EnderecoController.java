package com.login.apilogin.controller;

import com.login.apilogin.domain.Endereco;
import com.login.apilogin.service.EnderecoService;
import com.login.apilogin.service.impl.EnderecoServiceImpl;
import com.login.apilogin.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("enderecos")
@Log4j2
@RequiredArgsConstructor
public class EnderecoController {

    private final DateUtil dateUtil;
    private final EnderecoServiceImpl enderecoService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Endereco>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(enderecoService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.findById(id));
    }

    @PostMapping(path = "/saveEndereco")
    public ResponseEntity<Endereco> save(@RequestBody Endereco endereco){
        return new ResponseEntity<>(enderecoService.save(endereco), HttpStatus.CREATED);
    }

    @PutMapping(path = "/replaceEndereco")
    public ResponseEntity<Void> replace(@RequestBody Endereco endereco){
        enderecoService.replace(endereco);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

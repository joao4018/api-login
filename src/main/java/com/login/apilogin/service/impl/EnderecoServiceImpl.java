package com.login.apilogin.service.impl;

import com.login.apilogin.domain.Endereco;
import com.login.apilogin.repository.EnderecoRepository;
import com.login.apilogin.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Override
    public List<Endereco> listAll() {
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco findById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereco não Encontrado"));

    }

    @Override
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    public Endereco replace(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
}

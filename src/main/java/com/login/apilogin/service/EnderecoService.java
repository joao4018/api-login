package com.login.apilogin.service;

import com.login.apilogin.domain.Endereco;

import java.util.List;

public interface EnderecoService {

    List<Endereco> listAll();

    Endereco findById(Long id);

    Endereco save(Endereco endereco);

    Endereco replace(Endereco endereco);
}

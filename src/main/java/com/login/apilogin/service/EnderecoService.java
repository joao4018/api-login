package com.login.apilogin.service;

import com.login.apilogin.domain.Endereco;
import com.login.apilogin.request.EnderecoRequestBody;

import java.util.List;

public interface EnderecoService {

    List<Endereco> listAll();

    Endereco findById(Long id);

    Endereco save(EnderecoRequestBody enderecoRequestBody);

    Endereco replace(Endereco endereco);
}

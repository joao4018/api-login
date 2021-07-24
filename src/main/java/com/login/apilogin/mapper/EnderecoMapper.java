package com.login.apilogin.mapper;

import com.login.apilogin.domain.Endereco;
import com.login.apilogin.request.EnderecoRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class EnderecoMapper {
    public static final EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    public abstract Endereco toEndereco(EnderecoRequestBody enderecoRequestBody);
}

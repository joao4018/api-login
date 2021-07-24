package com.login.apilogin.mapper;

import com.login.apilogin.domain.Endereco;
import com.login.apilogin.domain.Endereco.EnderecoBuilder;
import com.login.apilogin.request.EnderecoRequestBody;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-24T12:49:59-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class EnderecoMapperImpl extends EnderecoMapper {

    @Override
    public Endereco toEndereco(EnderecoRequestBody enderecoRequestBody) {
        if ( enderecoRequestBody == null ) {
            return null;
        }

        EnderecoBuilder endereco = Endereco.builder();

        endereco.rua( enderecoRequestBody.getRua() );

        return endereco.build();
    }
}

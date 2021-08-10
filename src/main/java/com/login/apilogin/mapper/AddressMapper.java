package com.login.apilogin.mapper;

import com.login.apilogin.domain.Address;
import com.login.apilogin.request.AddressPutReplaceRequestBody;
import com.login.apilogin.request.AddressPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    public static final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    public abstract Address toAddress(AddressPostRequestBody addressRequestBody);

    public abstract Address toAddress(AddressPutReplaceRequestBody enderecoReplaceRequestBody);
}

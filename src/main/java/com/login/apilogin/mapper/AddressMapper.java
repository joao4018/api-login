package com.login.apilogin.mapper;

import com.login.apilogin.domain.Address;
import com.login.apilogin.request.AddressReplaceRequestBody;
import com.login.apilogin.request.AddressRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    public static final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    public abstract Address toAddress(AddressRequestBody addressRequestBody);

    public abstract Address toAddress(AddressReplaceRequestBody enderecoReplaceRequestBody);
}

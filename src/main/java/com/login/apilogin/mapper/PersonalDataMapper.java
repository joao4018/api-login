package com.login.apilogin.mapper;

import com.login.apilogin.domain.impl.PersonalData;
import com.login.apilogin.request.PersonalDataPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PersonalDataMapper {
    public static final PersonalDataMapper INSTANCE = Mappers.getMapper(PersonalDataMapper.class);

    public abstract PersonalData toPersonal(PersonalDataPostRequestBody personalDataPostRequestBody);

}

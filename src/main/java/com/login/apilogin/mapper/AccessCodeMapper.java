package com.login.apilogin.mapper;

import com.login.apilogin.domain.impl.AccessCode;
import com.login.apilogin.domain.impl.AccessUser;
import com.login.apilogin.dto.AccessCodeDto;
import com.login.apilogin.request.AccessCodePostRequestBody;
import com.login.apilogin.request.AccessPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AccessCodeMapper {
    public static final AccessCodeMapper INSTANCE = Mappers.getMapper(AccessCodeMapper.class);

    public abstract AccessCode toAccessCode(AccessCodePostRequestBody accessCodeRequestBody, AccessCodeDto accessCodeDto);

}

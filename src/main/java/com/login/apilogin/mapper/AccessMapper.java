package com.login.apilogin.mapper;

import com.login.apilogin.domain.impl.AccessUser;
import com.login.apilogin.request.AccessPostRequestBody;
import com.login.apilogin.response.SignupPostResponseBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author joaocarlos
 */
@Mapper(componentModel = "spring")
public abstract class AccessMapper {
    public static final AccessMapper INSTANCE = Mappers.getMapper(AccessMapper.class);

    public abstract AccessUser toAccess(AccessPostRequestBody accessRequestBody);
    public abstract SignupPostResponseBody toSignupPostResponseBody(AccessUser signupPostResponseBody);

}

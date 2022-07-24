package com.login.apilogin.service;

import com.login.apilogin.domain.impl.AccessCode;
import com.login.apilogin.request.AccessCodePostRequestBody;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccessCodeService {

    AccessCode createAccessCode(AccessCodePostRequestBody accessCodeRequestBody);
    AccessCode validateAccessCode(String code);

}

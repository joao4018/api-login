package com.login.apilogin.service.impl;

import com.login.apilogin.domain.impl.AccessCode;
import com.login.apilogin.dto.AccessCodeDto;
import com.login.apilogin.mapper.AccessCodeMapper;
import com.login.apilogin.repository.AccessCodeRepository;
import com.login.apilogin.request.AccessCodePostRequestBody;
import com.login.apilogin.service.AccessCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AccessCodeServiceImpl implements AccessCodeService {
    @Autowired
    private final AccessCodeRepository accessCodeRepository;

    @Override
    public AccessCode createAccessCode(AccessCodePostRequestBody accessCodeRequestBody) {
        LocalDateTime now = LocalDateTime.now();
        AccessCodeDto accessCodeDto = new AccessCodeDto(gerenateRandomCode(6), now, now.plusMinutes(5L));

        return accessCodeRepository.save(AccessCodeMapper.INSTANCE.toAccessCode(accessCodeRequestBody, accessCodeDto));
    }

    private String gerenateRandomCode(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}

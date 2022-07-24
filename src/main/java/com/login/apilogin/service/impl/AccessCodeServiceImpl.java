package com.login.apilogin.service.impl;

import com.login.apilogin.domain.impl.AccessCode;
import com.login.apilogin.dto.AccessCodeDto;
import com.login.apilogin.exception.BadRequestException;
import com.login.apilogin.mapper.AccessCodeMapper;
import com.login.apilogin.repository.AccessCodeRepository;
import com.login.apilogin.request.AccessCodePostRequestBody;
import com.login.apilogin.request.AccessPostRequestBody;
import com.login.apilogin.service.AccessCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.login.apilogin.constants.SystemConstantsExceptions.THIS_CODE_NOT_EXITS;
import static com.login.apilogin.constants.SystemConstantsExceptions.THIS_EMAIL_ALREADY_EXITS;
import static com.login.apilogin.constants.SystemConstantsExceptions.THIS_EMAIL_NOT_EXISTS;
import static com.login.apilogin.constants.SystemConstantsExceptions.THIS_USER_ALREADY_EXITS;


/**
 * @author joaocarlos
 */
@Service
@RequiredArgsConstructor
public class AccessCodeServiceImpl implements AccessCodeService {
    @Autowired
    private final AccessCodeRepository accessCodeRepository;

    @Override
    public AccessCode createAccessCode(AccessCodePostRequestBody accessCodeRequestBody) {
        LocalDateTime now = LocalDateTime.now();
        AccessCodeDto accessCodeDto = new AccessCodeDto(gerenateRandomCode(6), now, now.plusMinutes(5L));
        AccessCode accessCode = AccessCodeMapper.INSTANCE.toAccessCode(accessCodeRequestBody, accessCodeDto);
        accessCode.setValidated(Boolean.FALSE);
        return accessCodeRepository.save(accessCode);
    }

    @Override
    public AccessCode validateAccessCode(String code) {
        AccessCode accessCode = verifyCodeRegistered(code);
        accessCode.setValidated(Boolean.TRUE);
        return accessCodeRepository.save(accessCode);
    }

    private String gerenateRandomCode(int n) {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(alphaNumericString.length() * Math.random());

            sb.append(alphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public AccessCode verifyCodeRegistered(String code) {
        return accessCodeRepository.findByCode(code).orElseThrow(() -> new BadRequestException(THIS_CODE_NOT_EXITS));
    }

    public void validateByEmailAndCode(String email, String code) {
        accessCodeRepository.findByEmailAndCodeAndValidatedIsTrue(email, code)
                .orElseThrow(() -> new BadRequestException(THIS_EMAIL_NOT_EXISTS));
    }
}

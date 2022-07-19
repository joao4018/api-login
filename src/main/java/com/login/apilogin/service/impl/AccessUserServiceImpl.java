package com.login.apilogin.service.impl;

import com.login.apilogin.domain.impl.AccessUser;
import com.login.apilogin.domain.impl.PersonalData;
import com.login.apilogin.exception.BadRequestException;
import com.login.apilogin.mapper.AccessMapper;
import com.login.apilogin.mapper.PersonalDataMapper;
import com.login.apilogin.repository.AccessUserRepository;
import com.login.apilogin.repository.PersonalDataRepository;
import com.login.apilogin.request.AccessPostRequestBody;
import com.login.apilogin.request.PersonalDataPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static com.login.apilogin.constants.SystemConstantsExceptions.ACCESS_USER_NOT_FOUND;
import static com.login.apilogin.constants.SystemConstantsExceptions.THIS_EMAIL_ALREADY_EXITS;
import static com.login.apilogin.constants.SystemConstantsExceptions.THIS_USER_ALREADY_EXITS;
import static javax.management.timer.Timer.ONE_WEEK;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessUserServiceImpl implements UserDetailsService {
    private final AccessUserRepository accessUserRepository;

    private final PersonalDataRepository personalDataRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return accessUserRepository.findAccessByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(ACCESS_USER_NOT_FOUND));
    }

    public AccessUser createUser(AccessPostRequestBody accessRequestBody) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        accessRequestBody.setPassword(passwordEncoder.encode(accessRequestBody.getPassword()));

        AccessUser accessUser = AccessMapper.INSTANCE.toAccess(accessRequestBody);

        verifyUserRegistered(accessRequestBody);

        return accessUserRepository.save(builderUser(accessUser));
    }

    @Transactional
    public AccessUser addPersonalDataAtUser(PersonalDataPostRequestBody personalDataPostRequestBody, String username) {
        PersonalData personalData = PersonalDataMapper.INSTANCE.toPersonal(personalDataPostRequestBody);
        personalData = personalDataRepository.save(personalData);
        return accessUserRepository.save(builderAndUpdatePersonalData(personalData, username));
    }

    private AccessUser builderAndUpdatePersonalData(PersonalData personalData, String username) {
        AccessUser accessUser = accessUserRepository.findAccessByUserName(username)
                .orElseThrow(() -> new BadRequestException(ACCESS_USER_NOT_FOUND));
        return accessUser
                .toBuilder()
                .personalData(personalData)
                .build();
    }

    public AccessUser searchUserByEmail(String email) {
        return accessUserRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(email));
    }

    private AccessUser builderUser(AccessUser accessUser) {
        return accessUser
                .toBuilder()
                .singUpDate(LocalDateTime.now())
                .accountValidate(LocalDateTime.now().plusDays(TimeUnit.MILLISECONDS.toDays(ONE_WEEK))) // 7 days to confirm email
                .premiumValidate(LocalDateTime.now().plusDays(TimeUnit.MILLISECONDS.toDays(ONE_WEEK))) // 7 days premium free
                .passwordExpired(LocalDateTime.now().plusMonths(3L)) // after 90 days password expire
                .build();
    }

    private void verifyUserRegistered(AccessPostRequestBody accessRequestBody) {
        accessUserRepository.findAccessByUserName(accessRequestBody.getUserName())
                .ifPresent(user -> {
                    throw new BadRequestException(THIS_USER_ALREADY_EXITS);
                });

        accessUserRepository.findByEmail(accessRequestBody.getEmail())
                .ifPresent(email -> {
                    throw new BadRequestException(THIS_EMAIL_ALREADY_EXITS);
                });
    }
}

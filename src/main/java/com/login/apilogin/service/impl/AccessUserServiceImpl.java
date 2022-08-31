package com.login.apilogin.service.impl;

import com.login.apilogin.domain.impl.AccessEmail;
import com.login.apilogin.domain.impl.AccessUser;
import com.login.apilogin.domain.impl.PersonalData;
import com.login.apilogin.exception.BadRequestException;
import com.login.apilogin.kafkaConfig.KafkaConfig;
import com.login.apilogin.mapper.AccessMapper;
import com.login.apilogin.mapper.PersonalDataMapper;
import com.login.apilogin.repository.AccessEmailRepository;
import com.login.apilogin.repository.AccessUserRepository;
import com.login.apilogin.repository.PersonalDataRepository;
import com.login.apilogin.request.AccessPostRequestBody;
import com.login.apilogin.request.AccessRecoveryPostRequestBody;
import com.login.apilogin.request.PersonalDataPostRequestBody;
import com.login.apilogin.response.ResponseBody;
import com.login.apilogin.response.SignupPostResponseBody;
import com.login.apilogin.response.ValidateTokenResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import static com.login.apilogin.constants.SystemConstantsExceptions.EMAIL_ALREDY_SENT;
import static com.login.apilogin.constants.SystemConstantsExceptions.THIS_EMAIL_ALREADY_EXITS;
import static com.login.apilogin.constants.SystemConstantsExceptions.THIS_EMAIL_NOT_EXISTS;
import static com.login.apilogin.constants.SystemConstantsExceptions.THIS_USER_ALREADY_EXITS;
import static javax.management.timer.Timer.ONE_DAY;
import static javax.management.timer.Timer.ONE_WEEK;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessUserServiceImpl implements UserDetailsService {
    private final AccessUserRepository accessUserRepository;

    private final PersonalDataRepository personalDataRepository;

    private final AccessCodeServiceImpl accessCodeService;

    private final AccessEmailRepository accessEmailRepository;

    private final KafkaConfig kafkaConfig;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return accessUserRepository.findAccessByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException(ACCESS_USER_NOT_FOUND));
    }

    @Transactional
    public SignupPostResponseBody createUser(AccessPostRequestBody accessRequestBody) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        accessRequestBody.setPassword(passwordEncoder.encode(accessRequestBody.getPassword()));

        AccessUser accessUser = AccessMapper.INSTANCE.toAccess(accessRequestBody);

        verifyUserRegistered(accessRequestBody);

        kafkaConfig.produce(accessRequestBody.getEmail(), "Acesse o link para finalizar a criação da sua conta: ");

        return AccessMapper.INSTANCE.toSignupPostResponseBody(accessUserRepository.save(builderUser(accessUser)));
    }

    @Transactional
    public SignupPostResponseBody recoveryPassword(AccessRecoveryPostRequestBody accessRequestBody) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        accessCodeService.validateByEmailAndCode(accessRequestBody.getEmail(), accessRequestBody.getCode());

        AccessUser user = accessUserRepository.findByEmail(accessRequestBody.getEmail())
                .orElseThrow(() -> new BadRequestException(THIS_EMAIL_NOT_EXISTS));

        user.setPassword(passwordEncoder.encode(accessRequestBody.getPassword()));

        kafkaConfig.produce(accessRequestBody.getEmail(), "Acesse o link para finalizar a recuperação da sua senha: ");



        return AccessMapper.INSTANCE.toSignupPostResponseBody(accessUserRepository.save(user));
    }

    @Transactional
    public SignupPostResponseBody addPersonalDataAtUser(
            PersonalDataPostRequestBody personalDataPostRequestBody, String username) {
        PersonalData personalData = PersonalDataMapper.INSTANCE.toPersonal(personalDataPostRequestBody);
        personalData = personalDataRepository.save(personalData);
        return AccessMapper.INSTANCE
                .toSignupPostResponseBody(accessUserRepository
                        .save(builderAndUpdatePersonalData(personalData, username)));


    }

    public void validateUserAccount(String email) {
        AccessUser accessUser = accessUserRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(email));
        accessUser.setAccountValidate(accessUser.getAccountValidate().plusMonths(72));
        accessUserRepository.save(accessUser);
    }

    public SignupPostResponseBody searchUserByEmail(String email) {
        LocalDateTime now = LocalDateTime.now();
        AccessUser accessUser = accessUserRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(email));

        AccessEmail accessEmail = accessEmailRepository.findById(email)
                .orElse(new AccessEmail().toBuilder()
                        .email(email)
                        .registryDate(now)
                        .count(1L)
                        .build());

        validateLimitEmailSent(accessEmail);

        if (!accessEmail.getRegistryDate().equals(now)){
            accessEmail.setCount(accessEmail.getCount() + 1L);
        }

        accessEmailRepository.save(accessEmail);

        return AccessMapper.INSTANCE.toSignupPostResponseBody(accessUser);


    }

    private void validateLimitEmailSent(AccessEmail accessEmail) {
        if (accessEmail.getCount() > 2L){
            throw new ServiceException(EMAIL_ALREDY_SENT);
        }
    }

    private AccessUser builderUser(AccessUser accessUser) {
        return accessUser
                .toBuilder()
                .singUpDate(LocalDateTime.now())
                .accountValidate(LocalDateTime.now()
                        .plusDays(TimeUnit.MILLISECONDS.toDays(ONE_DAY))) // 7 days to confirm email
                .premiumValidate(LocalDateTime.now()
                        .plusDays(TimeUnit.MILLISECONDS.toDays(ONE_WEEK))) // 7 days premium free
                .passwordExpired(LocalDateTime.now()
                        .plusMonths(3L)) // after 90 days password expire
                .build();
    }

    private void verifyUserRegistered(AccessPostRequestBody accessRequestBody) {
        accessUserRepository.findByEmail(accessRequestBody.getEmail())
                .ifPresent(email -> {
                    throw new BadRequestException(THIS_EMAIL_ALREADY_EXITS);
                });

        accessUserRepository.findAccessByUsernameOrEmail(
                accessRequestBody.getUsername(),accessRequestBody.getUsername())
                .ifPresent(user -> {
                    throw new BadRequestException(THIS_USER_ALREADY_EXITS);
                });

    }

    private AccessUser builderAndUpdatePersonalData(PersonalData personalData, String username) {
        AccessUser accessUser = accessUserRepository.findAccessByUsernameOrEmail(username,username)
                .orElseThrow(() -> new BadRequestException(ACCESS_USER_NOT_FOUND));
        return accessUser
                .toBuilder()
                .personalData(personalData)
                .build();
    }

}

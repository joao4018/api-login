package com.login.apilogin.service.impl;

import com.login.apilogin.domain.impl.AccessUser;
import com.login.apilogin.mapper.AccessMapper;
import com.login.apilogin.repository.AccessUserRepository;
import com.login.apilogin.request.AccessPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static javax.management.timer.Timer.ONE_WEEK;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessUserServiceImpl implements UserDetailsService {
    private final AccessUserRepository accessUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return accessUserRepository.findAccessByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Access user not found!"));
    }


    public AccessUser createUser(AccessPostRequestBody accessRequestBody) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        accessRequestBody.setPassword(passwordEncoder.encode(accessRequestBody.getPassword()));

        AccessUser accessUser = AccessMapper.INSTANCE.toAccess(accessRequestBody);

        verifyUserRegistered(accessRequestBody);

        return accessUserRepository.save(builderUser(accessUser));
    }


    private AccessUser builderUser(AccessUser accessUser) {
        return accessUser
                .toBuilder()
                .singUpDate(LocalDateTime.now())
                .accountValidate(LocalDateTime.now().plusDays(ONE_WEEK))
                .premiumValidate(LocalDateTime.now().plusDays(ONE_WEEK))
                .build();
    }

    private void verifyUserRegistered(AccessPostRequestBody accessRequestBody) {
        accessUserRepository.findAccessByUserName(accessRequestBody.getUserName())
                .ifPresent(user -> {
                    throw new ServiceException("This user already exits");
                });

        accessUserRepository.findByEmail(accessRequestBody.getEmail())
                .ifPresent(email -> {
                    throw new ServiceException("This email already exits");
                });
    }
}

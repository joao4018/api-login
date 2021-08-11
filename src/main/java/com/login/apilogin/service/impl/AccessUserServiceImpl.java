package com.login.apilogin.service.impl;

import com.login.apilogin.domain.impl.AccessUser;
import com.login.apilogin.mapper.AccessMapper;
import com.login.apilogin.repository.AccessUserRepository;
import com.login.apilogin.request.AccessPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessUserServiceImpl implements UserDetailsService {
    private final AccessUserRepository accessUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return Optional.ofNullable(accessUserRepository.findAccessByUserName(userName))
                .orElseThrow(() -> new UsernameNotFoundException("Access user not found!"));
    }

    public AccessUser createUser(AccessPostRequestBody accessRequestBody){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        accessRequestBody.setPassword(passwordEncoder.encode(accessRequestBody.getPassword()));
        AccessUser accessUser = AccessMapper.INSTANCE.toAccess(accessRequestBody);
        return accessUserRepository.save(accessUser
                .toBuilder()
                .role("ROLE_USER")
                .build());
    }
}

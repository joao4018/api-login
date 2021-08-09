package com.login.apilogin.service.impl;

import com.login.apilogin.repository.AccessUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessUserServiceImpl implements UserDetailsService {
    private final AccessUserRepository accessUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return Optional.ofNullable(accessUserRepository.findAccessByUserName(userName))
                .orElseThrow(() -> new UsernameNotFoundException("Access user not found!"));
    }
}

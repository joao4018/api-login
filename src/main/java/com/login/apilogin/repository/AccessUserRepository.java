package com.login.apilogin.repository;

import com.login.apilogin.domain.impl.AccessUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessUserRepository extends JpaRepository<AccessUser, Long> {
    Optional<AccessUser> findAccessByUserName(String userName);
    Optional<AccessUser> findByEmail(String email);
}

package com.login.apilogin.repository;

import com.login.apilogin.domain.impl.AccessCode;
import com.login.apilogin.domain.impl.AccessUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author joaocarlos
 */
public interface AccessCodeRepository extends JpaRepository<AccessCode, String> {
    Optional<AccessCode> findByCode(String code);
    Optional<AccessCode> findByEmailAndCodeAndValidatedIsTrue(String email, String code);
}
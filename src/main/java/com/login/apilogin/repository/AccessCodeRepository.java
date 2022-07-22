package com.login.apilogin.repository;

import com.login.apilogin.domain.impl.AccessCode;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccessCodeRepository extends JpaRepository<AccessCode, String> {

}
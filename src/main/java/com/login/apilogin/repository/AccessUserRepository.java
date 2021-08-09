package com.login.apilogin.repository;

import com.login.apilogin.domain.impl.AccessUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessUserRepository extends JpaRepository<AccessUser, Long> {
    AccessUser findAccessByUserName(String userName);
}

package com.login.apilogin.repository;

import com.login.apilogin.domain.impl.AccessUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessUserRepository extends JpaRepository<AccessUser, Long> {
    Optional<AccessUser> findAccessByUserName(String userName);

    Optional<AccessUser> findByEmail(String email);

//    @Modifying
//    @Query(value = "update AccessUser u set u.firstname = ?1 where u.id = ?2", nativeQuery = true)
//    Optional<AccessUser> updateUser(Long personalData, Long userId);
}

package com.login.apilogin.repository;

import com.login.apilogin.domain.impl.AccessEmail;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author joaocarlos
 */
public interface AccessEmailRepository extends JpaRepository<AccessEmail, String> {
}
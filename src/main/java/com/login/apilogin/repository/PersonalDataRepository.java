package com.login.apilogin.repository;

import com.login.apilogin.domain.impl.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {

}

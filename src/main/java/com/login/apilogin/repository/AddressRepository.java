package com.login.apilogin.repository;

import com.login.apilogin.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByStreet(String street);
}

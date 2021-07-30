package com.login.apilogin.service;

import com.login.apilogin.domain.Address;
import com.login.apilogin.request.AddressReplaceRequestBody;
import com.login.apilogin.request.AddressRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    Page<Address> listAll(Pageable pageable);

    Address findById(Long id);

    List<Address> findByStreet(String street);

    Address save(AddressRequestBody addressRequestBody);

    Address replace(AddressReplaceRequestBody addressRequestBody);
}

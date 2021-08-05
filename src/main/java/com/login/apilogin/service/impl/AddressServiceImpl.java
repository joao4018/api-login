package com.login.apilogin.service.impl;

import com.login.apilogin.domain.Address;
import com.login.apilogin.exception.BadRequestException;
import com.login.apilogin.mapper.AddressMapper;
import com.login.apilogin.repository.AddressRepository;
import com.login.apilogin.request.AddressReplaceRequestBody;
import com.login.apilogin.request.AddressRequestBody;
import com.login.apilogin.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.login.apilogin.constants.SystemConstantsExceptions.ADDRESS_NOT_FOUND_BAD_REQUEST_EXCEPTION;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Page<Address> listAll(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ADDRESS_NOT_FOUND_BAD_REQUEST_EXCEPTION));

    }

    @Override
    public List<Address> findByStreet(String street) {
        return addressRepository.findByStreet(street);

    }

    @Override
    @Transactional
    public Address save(AddressRequestBody addressRequestBody) {
        return addressRepository.save(AddressMapper.INSTANCE.toAddress(addressRequestBody));
    }

    @Override
    public Address replace(AddressReplaceRequestBody addressReplaceRequestBody) {
        return addressRepository.save(AddressMapper.INSTANCE.toAddress(addressReplaceRequestBody));
    }
}

package com.login.apilogin.repository;

import com.login.apilogin.domain.Address;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static com.login.apilogin.builders.AddressBuilder.addressBuilder;
import static com.login.apilogin.builders.AddressBuilder.listAddressBuilder;
import static com.login.apilogin.constants.ConstantsForTest.DEFAULT_STREET_TEST;

@DataJpaTest
@DisplayName("Tests for AddresRepository.")
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Test
    @DisplayName("Test for save address.")
    void saveAddressTest() {
        Address address = addressRepository.save(addressBuilder());

        Assertions.assertThat(address).isNotNull();
        Assertions.assertThat(address.getStreet()).isEqualTo(address.getStreet());
        Assertions.assertThat(address.getId()).isNotNull();
    }

    @Test
    @DisplayName("Test for list address by street.")
    void listAddressByStreetTest() {
        List<Address> listAddress = new ArrayList<>();
        listAddressBuilder().forEach(address -> listAddress.add(addressRepository.save(address)));
        List<Address> byStreet = addressRepository.findByStreet(DEFAULT_STREET_TEST);
        List<Address> common = new ArrayList<>(listAddress);

        Assert.assertTrue(common.retainAll(byStreet));
    }

    @Test
    @DisplayName("Test for list address by street not found.")
    void listAddressByStreetEmptyTest() {
        List<Address> byStreet = addressRepository.findByStreet("STREET TEST NOT FOUND DATA");

        Assertions.assertThat(byStreet).isEmpty();
    }

    @Test
    @DisplayName("Test for list address by street null.")
    void listAddressByStreetNullTest() {
        List<Address> byStreet = addressRepository.findByStreet(null);

        Assertions.assertThat(byStreet).isEmpty();
    }

    @Test
    @DisplayName("Test for list address by two same street.")
    void listAddressByStreetSameTest() {
        List<Address> listAddress = new ArrayList<>();
        listAddress.add(addressRepository.save(addressBuilder()));
        listAddress.add(addressRepository.save(addressBuilder()));
        List<Address> byStreet = addressRepository.findByStreet(DEFAULT_STREET_TEST);

        Assertions.assertThat(byStreet).isEqualTo(listAddress);
    }

}
//package com.login.apilogin.controller;
//
//import com.login.apilogin.builders.AddressBuilder;
//import com.login.apilogin.domain.Address;
//import com.login.apilogin.service.impl.AddressServiceImpl;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Arrays;
//
//
//@ExtendWith(SpringExtension.class)
//class AddressControllerTest {
//
//    @InjectMocks
//    AddressController addressController;
//
//    @Mock
//    AddressServiceImpl addressService;
//
//    @BeforeEach
//    void setUp() {
//        PageImpl<Address> address = new PageImpl<>(Arrays.asList(AddressBuilder.addressBuilder()));
//        BDDMockito.when(addressService.listAll(ArgumentMatchers.any())).thenReturn(address);
//    }
//
//    @Test
//    void listAllTest() {
//        ResponseEntity<Page<Address>> list = addressController.list(null);
//        Assertions.assertThat(list.getBody().getContent())
//                .isEqualTo(Arrays.asList(AddressBuilder.addressBuilder()));
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void findByIdStreet() {
//    }
//
//    @Test
//    void save() {
//    }
//
//    @Test
//    void replace() {
//    }
//}
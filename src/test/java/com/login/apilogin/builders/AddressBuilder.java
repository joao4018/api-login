package com.login.apilogin.builders;

import com.login.apilogin.domain.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.login.apilogin.constants.ConstantsForTest.DEFAULT_STREET_TEST;
import static com.login.apilogin.constants.ConstantsForTest.DEFAULT_STREET_TEST2;

public class AddressBuilder {


    public static final Address addressBuilder() {
        return new Address().toBuilder()
                .street(DEFAULT_STREET_TEST)
                .build();
    }

    public static final Address addressBuilder2() {
        return new Address().toBuilder()
                .street(DEFAULT_STREET_TEST2)
                .build();
    }

    public static final List<Address> listAddressBuilder() {
        return new ArrayList<>(Arrays.asList(addressBuilder(), addressBuilder2()));
    }
}

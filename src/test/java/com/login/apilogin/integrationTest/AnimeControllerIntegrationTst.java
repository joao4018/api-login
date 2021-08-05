package com.login.apilogin.integrationTest;

import com.login.apilogin.builders.AddressBuilder;
import com.login.apilogin.domain.Address;
import com.login.apilogin.repository.AddressRepository;
import com.login.apilogin.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class AnimeControllerIntegrationTst {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    AddressRepository addressRepository;

    @Test
    @DisplayName("list returns list of address inside page object when succesful")
    void listReturnAddresInsadePage() {

        addressRepository.save(AddressBuilder.addressBuilder());

        String expectedName = AddressBuilder.addressBuilder().getStreet();

        PageableResponse<Address> addressPageableResponse = restTemplate
                .exchange("/addresses/list", HttpMethod.GET, null,
                        new ParameterizedTypeReference<PageableResponse<Address>>() {
                        }).getBody();

        Assertions.assertThat(addressPageableResponse).isNotNull();

        Assertions.assertThat(addressPageableResponse.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(addressPageableResponse.toList().get(0).getStreet()).isEqualTo(expectedName);
    }

}

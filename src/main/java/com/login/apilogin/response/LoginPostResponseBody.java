package com.login.apilogin.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author joaocarlos
 */
@Data
@Builder(toBuilder = true)
public class LoginPostResponseBody {

    private String name;
    private String token;

}

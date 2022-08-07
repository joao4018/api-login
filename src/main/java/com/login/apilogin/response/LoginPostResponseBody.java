package com.login.apilogin.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author joaocarlos
 */
@Data
@Builder(toBuilder = true)
public class LoginPostResponseBody extends GenericResponse {

    private static final long serialVersionUID = 3296855311118968601L;
    private String name;
    private String token;

}

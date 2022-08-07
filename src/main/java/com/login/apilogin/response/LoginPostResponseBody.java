package com.login.apilogin.response;

import com.login.apilogin.response.GenericResponseAdapters.GenericResponse;
import lombok.Builder;
import lombok.Data;

/**
 * @author joaocarlos
 */
@Data
@Builder(toBuilder = true)
public class LoginPostResponseBody extends GenericResponse {

    private String name;
    private String token;

}

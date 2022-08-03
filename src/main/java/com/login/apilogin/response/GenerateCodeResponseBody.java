package com.login.apilogin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author joaocarlos
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class GenerateCodeResponseBody implements GenericResponse {

    private String code;

}

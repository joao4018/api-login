package com.login.apilogin.response;

import com.login.apilogin.response.GenericResponseAdapters.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author joaocarlos
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class GenerateCodeResponseBody extends GenericResponse {

    private String code;

}

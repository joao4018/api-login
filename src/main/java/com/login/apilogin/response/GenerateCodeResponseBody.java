package com.login.apilogin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author joaocarlos
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@Data

public class GenerateCodeResponseBody extends GenericResponse {

    private static final long serialVersionUID = 5949423012109318131L;
    private String code;

}

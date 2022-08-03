package com.login.apilogin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author joaocarlos
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBody{

    private String message;
    private GenericResponse data;
    protected String title;
    protected Integer status;
    protected String details;
    protected LocalDateTime timestamp = LocalDateTime.now();


}

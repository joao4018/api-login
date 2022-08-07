package com.login.apilogin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author joaocarlos
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseBody{

    private String message;
    private GenericResponse data;
    private String title;
    private Integer status;
    private String details;
    private LocalDateTime timestamp = LocalDateTime.now();


}

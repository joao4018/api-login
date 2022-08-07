package com.login.apilogin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author joaocarlos
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseBody implements Serializable{

    private static final long serialVersionUID = 7107906189421273428L;
    private String message;
    private GenericResponse data;
    private String title;
    private Integer status;
    private String details;
    private LocalDateTime timestamp = LocalDateTime.now();


}

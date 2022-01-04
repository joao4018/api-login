package com.login.apilogin.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseBody {

    private Boolean status;
    private String message;
    private Object data;

}

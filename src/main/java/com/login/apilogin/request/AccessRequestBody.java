package com.login.apilogin.request;

import lombok.Data;

@Data
public class AccessRequestBody {

    private String userName;
    private String password;
}

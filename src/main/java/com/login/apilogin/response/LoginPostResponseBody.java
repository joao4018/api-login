package com.login.apilogin.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class LoginPostResponseBody {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String image;
    private String points;
    private String credit;
    private String password;
    private String token;

}

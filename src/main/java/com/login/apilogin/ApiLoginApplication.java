package com.login.apilogin;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.login.apilogin.constants.Asg.Asg.AUTHOR_LINKEDIN;
import static com.login.apilogin.constants.Asg.Asg.AUTHOR_SIGNATURE;

@SpringBootApplication
@Log4j2
public class ApiLoginApplication {

    public static void main(String[] args) {
        log.info(AUTHOR_SIGNATURE);
        log.info(AUTHOR_LINKEDIN);
        SpringApplication.run(ApiLoginApplication.class, args);
    }

}

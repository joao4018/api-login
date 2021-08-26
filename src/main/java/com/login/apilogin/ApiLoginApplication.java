package com.login.apilogin;

import com.login.apilogin.config.jwt.JwtConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import static com.login.apilogin.constants.Asg.Asg.AUTHOR_LINKEDIN;
import static com.login.apilogin.constants.Asg.Asg.AUTHOR_SIGNATURE;


@SpringBootApplication
@Log4j2
@EnableConfigurationProperties(value = JwtConfiguration.class)
@EnableEurekaClient
public class ApiLoginApplication {

    public static void main(String[] args) {
        log.info(AUTHOR_SIGNATURE);
        log.info(AUTHOR_LINKEDIN);
        SpringApplication.run(ApiLoginApplication.class, args);
    }

}

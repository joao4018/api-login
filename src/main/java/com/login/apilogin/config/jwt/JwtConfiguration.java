package com.login.apilogin.config.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author joao4018
 */
@Configuration
@ConfigurationProperties(prefix = "security")
@Getter
@Setter
@ToString
public class JwtConfiguration {
    private String loginUrl = "**/login/**";
    @NestedConfigurationProperty
    private Header header = new Header();
    private int expiration = 3600;
    @Value("${spring.encr}")
    private String privateKey;
    private String type = "encrypted";

    @Getter
    @Setter
    public static class Header {
        private String name = "Authorization";
        private String prefix = "Bearer ";
    }
}
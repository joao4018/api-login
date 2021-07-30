package com.login.apilogin.apiLoginWebMvcConfigurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.login.apilogin.ApiParameters.SystemParameters.PAGE_INIT;
import static com.login.apilogin.ApiParameters.SystemParameters.PAGE_SIZE;

@Configuration
public class ApiLoginWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver pageableHandler = new PageableHandlerMethodArgumentResolver();
        pageableHandler.setFallbackPageable(PageRequest.of(PAGE_INIT, PAGE_SIZE));
    }
}

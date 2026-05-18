package com.jayant.post_Service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Registers the interceptor with Spring MVC

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private  UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor);
    }

    //Spring doesn't auto-discover interceptors. WebMvcConfigurer.addInterceptors() is the official hook to register them.
    // Without this, UserInterceptor would be a Spring bean that never actually intercepts anything.
}

package com.taskservice.task.Configurers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: task--com.taskservice.task.Configurers
 * @author: WaHotDog 2019-03-14 00:01
 **/

@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        loginRegistry.addPathPatterns("/**");
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/fail");
        loginRegistry.excludePathPatterns("/success");
        loginRegistry.excludePathPatterns("/api/**");

    }
}

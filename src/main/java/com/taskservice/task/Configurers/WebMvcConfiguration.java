package com.taskservice.task.Configurers;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: task--com.taskservice.task.Configurers
 * @author: WaHotDog 2019-05-09 07:15
 **/


public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/a/**").addResourceLocations("file:D://whdIDEASpace/SZCS/src/main/resources/a/a");
        super.addResourceHandlers(registry);
    }
}

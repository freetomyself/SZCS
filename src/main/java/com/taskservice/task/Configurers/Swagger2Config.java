package com.taskservice.task.Configurers;

/**
 * @program: task--com.taskservice.task.Configurers
 * @author: WaHotDog 2019-03-07 18:15
 **/


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @ClassName:  Swagger2Config
 * @Description:TODO(swagger 配置)
 * @author: drj
 * @date:   2019年1月19日 下午8:10:52
 *
 * @Copyright: 2019
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.taskservice.task")).paths(PathSelectors.any())
                .build();
    }//PathSelectors.regex("/v1/")

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("注册登陆 API").description("注册登陆 API")
                .version("2.0").build();

    }
}


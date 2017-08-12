package com.test.dana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mczal on 8/10/17.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  private ApiInfo apiInfo() {
    ApiInfo apiInfo =
        new ApiInfo(
            "Messaging Security Dana Test API - Fahrizal",
            "Fahrizal API.",
            "v1",
            "Terms of service",
            new Contact("Mohamad Fahrizal Septrianto",
                "https://github.com/mczal",
                "fahrizalseptrianto@gmail.com"),
            "License of API",
            "API license URL");
    return apiInfo;
  }

  @Bean
  public Docket api() {
    List<Parameter> objectList = new ArrayList<>();
    objectList
        .add(new ParameterBuilder()
            .name("Authorization")
            .description("key")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build());

    return new Docket(DocumentationType.SWAGGER_2)
        .globalOperationParameters(objectList)
        .select()
        .apis(RequestHandlerSelectors
            .basePackage("com.test.dana.controller.rest"))
        .paths(PathSelectors
            .ant("/api/**"))
        .build()
        .apiInfo(apiInfo());
  }

}

package com.icaetano.beerprint.security.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.icaetano.beerprint.security.model.Usuario;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfigurations {
  @Bean
  public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.basePackage("com.icaetano.beerprint"))
              .paths(PathSelectors.ant("/**"))
              .build()
              .ignoredParameterTypes(Usuario.class)
              .globalOperationParameters(
                      Arrays.asList(
                              new ParameterBuilder()
                                  .name("Authorization")
                                  .description("Header para Token JWT")
                                  .modelRef(new ModelRef("string"))
                                  .parameterType("header")
                                  .required(false)
                                  .build()));
  }
}
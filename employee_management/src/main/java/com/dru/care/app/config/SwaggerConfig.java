package com.dru.care.app.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	Docket getDocket() {

		ApiInfo apiInfo = new ApiInfo("Employee-App", "SwaggerInterface", "2.7.0", "http://www.drucare.com",
				new Contact("Konda Raghu", "http://www.drucare.com", "kondaraghu0218@gmail.com"), "DC-2023",
				"http://www.drucare.com", new ArrayList<>());

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.dru.care.app")).build().apiInfo(apiInfo)
				.useDefaultResponseMessages(false);

	}
}

package com.example.Library.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

//visit for documentation-> http://localhost:8080/swagger-ui.html#/
public class LibraryManagementApp {

	private static final String BASE_PACKAGE = "com.example.Library.App.Controller";
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApp.class, args);
	}

	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))//It will allows Apis of this package only.
				.build();
	}
}

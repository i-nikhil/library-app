package com.example.Library.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

//visit for documentation-> http://localhost:8080/swagger-ui.html#/
public class LibraryManagementApp {

	public static void main(String[] args) {

		SpringApplication.run(LibraryManagementApp.class, args);
	}

}

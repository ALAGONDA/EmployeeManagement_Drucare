package com.dru.care.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource(value = "classpath:sql.properties", ignoreResourceNotFound = true)
public class EmployeeCurdOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeCurdOperationApplication.class, args);
	}

}

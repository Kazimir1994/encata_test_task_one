package ru.kazimir.bortnik.controller_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.kazimir.bortnik")
public class SpringBootModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootModuleApplication.class, args);
	}

}

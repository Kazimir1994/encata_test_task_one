package ru.kazimir.bortnik.controller_module.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "ru.kazimir.bortnik")
@EnableMongoRepositories("ru.kazimir.bortnik.repository_module")
public class SpringBootModuleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootModuleApplication.class, args);
	}
}

package com.w2m.supermaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SupermaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupermaintenanceApplication.class, args);
	}

}

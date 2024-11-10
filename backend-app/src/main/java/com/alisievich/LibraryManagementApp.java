package com.alisievich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LibraryManagementApp {
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApp.class, args);
	}
}

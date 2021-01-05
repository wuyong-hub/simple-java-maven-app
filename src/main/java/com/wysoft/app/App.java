package com.wysoft.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@SpringBootApplication
@RestController
public class App {

	private String message = "Hello World!";
	
	public App() {
		message = "Hello,Jenkins!";
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	public static void printMessage(String []args) {
		System.out.println(new App().getMessage());
	}

	private final String getMessage() {
		return message;
	}

	@RequestMapping("/test")
	public String test() {
		return "TEST";
	}
}

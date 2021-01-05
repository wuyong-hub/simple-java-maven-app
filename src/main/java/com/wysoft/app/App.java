package com.wysoft.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {

	private String message = "Hello World!";

	public App() {
		message = "Hello,Jenkins!";
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println(new App().getMessage());
	}

	private final String getMessage() {
		return message;
	}

	public String test() {
		return "TEST";
	}
}

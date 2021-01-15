package com.wysoft.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wysoft.app.service.TestService;

/**
 * Hello world!
 */
@SpringBootApplication
@RestController
public class App {
	@Autowired
	private TestService service;

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
	
	@RequestMapping("/")
	public String home() {
		JSONObject result = new JSONObject();
		result.put("code", 200);
		result.put("message", "Success");
		return result.toString();
	}

	@RequestMapping("/test/{id:\\d+}")
	public JSONObject test(@PathVariable Long id) {
		return service.getUserById(id);
	}
}

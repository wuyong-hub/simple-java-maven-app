package com.mycompany.app;

/**
 * Hello world!
 */
public class App
{

    private String message = "Hello World!";

    public App() {
	message = "Hello,Jenkins!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getMessage());
    }

    private final String getMessage() {
        return message;
    }

    private final String sayHello(){
	return "Hello World!";
    }
    
    public String test(){
 	return "TEST";
    }
}

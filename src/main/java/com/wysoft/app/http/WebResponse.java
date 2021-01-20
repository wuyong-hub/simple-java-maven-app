package com.wysoft.app.http;

public class WebResponse {
	private int code;
	private String message;
	private Object body;

	public WebResponse(int code, String message, Object body) {
		super();
		this.code = code;
		this.message = message;
		this.body = body;
	}

	public static WebResponse getSuccessResponse(Object data) {
		return new WebResponse(200, "Success", data);
	}

	public static WebResponse getFailResponse(String message) {
		return new WebResponse(200, message, null);
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getBody() {
		return this.body;
	}
}

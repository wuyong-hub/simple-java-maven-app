package com.wysoft.app.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class TestService {
	public JSONObject getUserById(Long id) {
		JSONObject user = new JSONObject();
		user.put("code", 200);
		user.put("message", "Success");
		user.put("name", "Kevin");
		return user;
	}
}

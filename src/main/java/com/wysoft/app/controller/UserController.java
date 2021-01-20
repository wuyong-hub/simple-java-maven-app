package com.wysoft.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wysoft.app.http.WebResponse;
import com.wysoft.app.model.User;
import com.wysoft.app.service.UserService;

/**
 * Restful API接口
 * @author Wuyong 2021-01-18
 *
 */
@RestController
@RequestMapping("/rest")
public class UserController {
	@Autowired
	private UserService userv;

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
	public WebResponse saveUser(@RequestBody User user) {
		user = userv.add(user);
		return WebResponse.getSuccessResponse(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json")
	public WebResponse listUser() {
		List<User> users = userv.get();
		return WebResponse.getSuccessResponse(users);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
	public WebResponse get(@PathVariable String id) {
		User user = userv.get(id);
		return WebResponse.getSuccessResponse(user);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces = "application/json")
	public WebResponse updateUser(@PathVariable String id, @RequestBody User user) {
		user.setId(id);
		userv.update(user);
		return WebResponse.getSuccessResponse(null);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public WebResponse removeUser(String id) {
		userv.remove(id);
		return WebResponse.getSuccessResponse(null);
	}
}

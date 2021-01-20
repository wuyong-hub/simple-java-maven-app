package com.wysoft.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.UnsupportedEncodingException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.wysoft.app.model.User;
import com.wysoft.app.service.TestService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getUserById() throws Exception {
		TestService service = mock(TestService.class);
		JSONObject user = new JSONObject();
		user.put("code", 200);
		user.put("message", "Success");
		user.put("name", "Kevin");
		// Mock一个结果，当userService调用getById的时候，返回user
		doReturn(user).when(service).getUserById(any());

		// perform,执行一个RequestBuilders请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理
		mockMvc.perform(MockMvcRequestBuilders
				// 构造一个get请求
				.get("/test/1")
				// 请求类型 json
				.contentType(MediaType.APPLICATION_JSON))
				// 期望的结果状态 200
				.andExpect(MockMvcResultMatchers.status().isOk())
				// 添加ResultHandler结果处理器，比如调试时 打印结果(print方法)到控制台
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalToIgnoringCase("kevin")))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		user.setName("王小二");
		user.setUsername("Jim");
		mockMvc.perform(MockMvcRequestBuilders.post("/rest/user")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(JSONObject.toJSONString(user)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
	}

	@Test
	public void testGetUser() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/user/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testListUser() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/rest/users")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
}

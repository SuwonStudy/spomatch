package com.spomatch.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import utils.SpringTest;

@AutoConfigureMockMvc
public class TestUserController extends SpringTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected UserRepository repo;
	
	@Test
	public void User_회원가입() throws Exception {
		
		User toRegister = UserTests.createDummy();
		
		String toRegisterJson = mapper.writeValueAsString(toRegister);
		
		String returnedJson = mockMvc.perform(
			// POST 요청
			post("/users")
			.content(toRegisterJson)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
		// OK 응답
		.andExpect(status().isOk())
		// JSON으로 가져옴
		.andReturn().getResponse().getContentAsString();

		User registered = mapper.readValue(returnedJson, User.class);
		
		assertNotNull(registered.getId()); // ID 부여됨
	}

	@Test
	public void User_로그인() throws Exception {
		
		User registered = registerDummy();
		
		UserCredentials credentials = registered.getUserCredentials();
		
		String credentialsJson = mapper.writeValueAsString(credentials);
		
		String returnedJson = mockMvc.perform(
			// POST 요청
			post("/users/login")
			.content(credentialsJson)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
		// OK 응답
		.andExpect(status().isOk())
		// JSON으로 가져옴
		.andReturn().getResponse().getContentAsString();

		User loggedIn = mapper.readValue(returnedJson, User.class);
		
		assertEquals(loggedIn.getUserCredentials(), credentials);
	}
	
	@Test
	public void User_탈퇴() throws Exception {

		User registered = registerDummy();
		
		Long id = registered.getId();
		mockMvc.perform(
			// DELETE 요청
			delete("/users/" + id)
		)
		// OK 응답
		.andExpect(status().isOk());
	}
}

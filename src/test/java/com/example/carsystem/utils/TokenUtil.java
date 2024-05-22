package com.example.carsystem.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.carsystem.dto.SigninRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TokenUtil {

	@Autowired
	private ObjectMapper objectMapper;

	public String obtainAccessToken(MockMvc mockMvc, String existsUserLogin, String existsUserPassword) throws Exception {

		SigninRequest signinRequest = new SigninRequest(existsUserLogin, existsUserPassword);

		String jsonBody = objectMapper.writeValueAsString(signinRequest);

		ResultActions result =
				mockMvc.perform(post("/api/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonBody)
						.accept(MediaType.APPLICATION_JSON));

		return result.andReturn().getResponse().getContentAsString();
	}
}


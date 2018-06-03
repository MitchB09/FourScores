package com.realscores.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseControllerTest {
	  
	protected String jsonify(Object obj) throws JsonProcessingException	{
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(obj);	  
	}
}

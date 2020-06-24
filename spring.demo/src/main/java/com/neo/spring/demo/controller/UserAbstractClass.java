package com.neo.spring.demo.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.spring.demo.bean.DashboardResponse;
import com.neo.spring.demo.bean.UserBean;
import com.neo.spring.demo.serviceImpl.UserServiceImpl;

public class UserAbstractClass {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	
	public String validate(String request) throws Exception {
		String returnValue=null;
		DashboardResponse dashboardResponse = new DashboardResponse();
		UserBean req = MAPPER.readValue(request, UserBean.class);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UserBean>> violations = validator.validate(req);
		for (ConstraintViolation<UserBean> violation : violations) {
		    LOGGER.error(violation.getMessage());
		    dashboardResponse.setResponseData(violation.getPropertyPath().toString() ,violation.getMessage());
		   returnValue = MAPPER.writeValueAsString(dashboardResponse);
		   return returnValue;
		}
		return "pass";
	}

}

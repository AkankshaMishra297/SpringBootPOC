package com.neo.spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neo.spring.demo.model.User;
import com.neo.spring.demo.model.UserData;
import com.neo.spring.demo.service.UserService;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(@RequestBody String dashboardRequest) throws Exception {
		LOGGER.trace("Starting getUserByFirstNameAndLastName() from UserController with arguments:: dashboardRequest: "+dashboardRequest);
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.addUser(dashboardRequest);
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting addUser() from UserController with return:: responseEntity: "+responseEntity);
		return responseEntity;
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsers() throws Exception {
		LOGGER.trace("Starting getUsers() from UserController");
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.getAllActiveUsers();
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting getUsers() from UserController");
		return responseEntity;
	}

	@RequestMapping(value = "/softDelete/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> softDeleteUser(@PathVariable("id")int id) throws Exception {
		LOGGER.trace("Starting hardDeleteUser() from UserController");
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.softDelete(id);
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting softDeleteUser() from UserController");
		return responseEntity;
	}

	@RequestMapping(value = "/hardDelete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> hardDeleteUser(@PathVariable("id")int id) throws Exception {
		LOGGER.trace("Starting hardDeleteUser() from UserController");
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.deleteUser(id);
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting hardDeleteUser() from UserController");
		return responseEntity;
	}

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers() throws Exception {
		LOGGER.trace("Starting getAllUsers() from UserController");
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.getAllUsers();
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting getAllUsers() from UserController");
		return responseEntity;
	}


	@RequestMapping(value = "/searchByEmail/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchByEmail(@PathVariable("email") String email) throws Exception {
		LOGGER.trace("Starting searchByEmail() from UserController");
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.searchByEmail(email);
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting searchByEmail() from UserController");
		return responseEntity;
	}
	
	@PutMapping("/editUser")
	public User editUser(@RequestBody User user){
		return userService.editUser(user);
	}







}

package com.neo.spring.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neo.spring.demo.service.UserService;

@RestController
public class UserController extends UserValidation {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	//Add user
	@PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(@RequestBody String dashboardRequest) throws Exception {
		LOGGER.trace("Starting addUser() from UserController with arguments:: dashboardRequest: "+dashboardRequest);
		ResponseEntity<?> responseEntity = null;
		if(validate(dashboardRequest).equals("pass")) {
			String jsonString = userService.addUser(dashboardRequest);
			if(jsonString != null){
				responseEntity = ResponseEntity.ok(jsonString);
			} else
				responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			responseEntity = ResponseEntity.ok(validate(dashboardRequest));
		}
		LOGGER.trace("Exiting addUser() from UserController with return:: responseEntity: "+responseEntity);
		return responseEntity;
	}
	
	//Edit User with given id
	@PutMapping(value = "/editUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editUser(@RequestBody String dashboardRequest, @PathVariable("id") int id) throws Exception {
		LOGGER.trace("Starting editUser() from UserController");
		ResponseEntity<?> responseEntity = null;
				if(validate(dashboardRequest).equals("pass")) {
			String jsonString = userService.editUser(dashboardRequest, id);
			if(jsonString != null){
				responseEntity = ResponseEntity.ok(jsonString);
			} else
				responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			responseEntity = ResponseEntity.ok(validate(dashboardRequest));
		}
		LOGGER.trace("Exiting editUser() from UserController");
		return responseEntity;
	}

	//Get active users
	@GetMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	//Get all users based on pagination 
	@GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers(@RequestBody String DashBoardRequest) throws Exception {
		LOGGER.trace("Starting getAllUsers() from UserController");
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.getAllUsers(DashBoardRequest);
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting getAllUsers() from UserController");
		return responseEntity;
	}

	//soft delete
	@PutMapping(value = "/softDelete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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

	//hard delete
	@DeleteMapping(value = "/hardDelete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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

	//search user by email
	@GetMapping(value = "/searchByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	//sort user based on any field
	@RequestMapping(value = "/sortBy/{anything}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sortByAnything(@PathVariable("anything") String anything) throws Exception {
		LOGGER.trace("Starting sortByAnything() from UserController");
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.sortBy(anything);
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting sortByAnything() from UserController");
		return responseEntity;
	}
}

package com.neo.spring.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.spring.demo.bean.DashboardResponse;
import com.neo.spring.demo.common.CommonConstants;
import com.neo.spring.demo.model.User;
import com.neo.spring.demo.repository.UserRepository;
import com.neo.spring.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final String USER_DETAILS = "user_details";

	@Autowired
	private UserRepository userRepo;

	@Override
	public String addUser(String dashboardRequest) throws Exception {
		LOGGER.trace("Starting getUserByFirstNameAndLastName() from UserServiceImpl with arguments:: dashboardRequest: "+dashboardRequest);
		String returnValue = null;
		String errorMsg = null;
		DashboardResponse dashboardResponse = new DashboardResponse();
		try {
			User requestJsonNode = MAPPER.readValue(dashboardRequest, User.class);
			//String user = requestJsonNode.get("User").asText();

			User user1 = this.userRepo.save(requestJsonNode);
			if(user1 != null) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS," User saved" );
			} else
				errorMsg = "No Records found for requested input.";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting getUserByFirstNameAndLastName() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}

	@Override
	public String getAllActiveUsers() throws Exception {
		LOGGER.trace("Starting getAllActiveUsers() from UserServiceImpl");
		String returnValue = null;
		String errorMsg = null;
		DashboardResponse dashboardResponse = new DashboardResponse();
		try {

			List<User> userDetailsList = this.userRepo.findActiveUser();
			LOGGER.trace("USER_DETAILS_LIST:: "+userDetailsList);
			if(!userDetailsList.isEmpty()) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS, userDetailsList);
			} else
				errorMsg = "No Records found for requested input.";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting getAllActiveUsers() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}

	@Override
	public String deleteUser(int id) throws Exception {
		LOGGER.trace("Starting deleteUser() from UserServiceImpl");
		String returnValue = null;
		String errorMsg = null;
		DashboardResponse dashboardResponse = new DashboardResponse();
		try {
			Optional<User> u = userRepo.findById(id);

			if(u.isPresent()) {
				this.userRepo.deleteById(id);
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS, "User Deleted");
			} else
				errorMsg = "No User found for this ID";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting deleteUser() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}

	@Override
	public String softDelete(int id) throws Exception {
		LOGGER.trace("Starting deleteUser() from UserServiceImpl");
		String returnValue = null;
		String errorMsg = null;
		DashboardResponse dashboardResponse = new DashboardResponse();
		try {
			User user = userRepo.findById(id).get();

			if(user!=null) {
				user.setActive(false);
				userRepo.save(user);
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS, "User Deleted");
			} else
				errorMsg = "No User found for this ID";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting deleteUser() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}

	@Override
	public String getAllUsers() throws Exception {
		LOGGER.trace("Starting getAllUsers() from UserServiceImpl");
		String returnValue = null;
		String errorMsg = null;
		DashboardResponse dashboardResponse = new DashboardResponse();
		try {

			List<User> userDetailsList = this.userRepo.findAll();
			LOGGER.trace("USER_DETAILS_LIST:: "+userDetailsList);
			if(!userDetailsList.isEmpty()) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS, userDetailsList);
			} else
				errorMsg = "No Records found for requested input.";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting getAllUsers() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}

	@Override
	public String searchByEmail(String email) throws Exception {
		LOGGER.trace("Starting searchByEmail() from UserServiceImpl");
		String returnValue = null;
		String errorMsg = null;
		DashboardResponse dashboardResponse = new DashboardResponse();
		try {

			List<User> userDetailsList = this.userRepo.findByUserDetailsEmail(email);
			LOGGER.trace("USER_DETAILS_LIST:: "+userDetailsList);
			if(!userDetailsList.isEmpty()) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS, userDetailsList);
			} else
				errorMsg = "No Records found for requested input.";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting searchByEmail() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}



	@Override
	public User editUser(User user) {
		return userRepo.save(user);
	}




}

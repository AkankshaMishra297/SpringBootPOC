package com.neo.spring.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.spring.demo.bean.DashboardResponse;
import com.neo.spring.demo.bean.UserBean;
import com.neo.spring.demo.bean.UserDetailsBean;
import com.neo.spring.demo.bean.UserEducationBean;
import com.neo.spring.demo.bean.UserEmploymentBean;
import com.neo.spring.demo.common.CommonConstants;
import com.neo.spring.demo.model.User;
import com.neo.spring.demo.model.UserDetails;
import com.neo.spring.demo.model.UserEducation;
import com.neo.spring.demo.model.UserEmployment;
import com.neo.spring.demo.repository.UserRepository;
import com.neo.spring.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final String USER_DETAILS = "user_info";

	@Autowired
	private UserRepository userRepo;

	@Override
	public String addUser(String dashboardRequest) throws Exception {
		LOGGER.trace("Starting addUser() from UserServiceImpl with arguments:: dashboardRequest: "+dashboardRequest);
		String returnValue = null;
		String errorMsg = null;
		DashboardResponse dashboardResponse = new DashboardResponse();
		try {
			UserBean request = MAPPER.readValue(dashboardRequest, UserBean.class);
			System.out.println(request);
			User user = new User();
			user.setActive(true);
			user.setUserName(request.getUserName());
			user.setPassword(request.getUserPassword());

			UserDetails userDetails = new UserDetails();
			userDetails.setUser(user);
			userDetails.setFirstName(request.getUserDetails().getFirstName());
			userDetails.setLastName(request.getUserDetails().getLastName());
			//userDetails.setAddress(request.getUserDetails().getAddress());
			userDetails.setEmail(request.getUserDetails().getEmail());
			userDetails.setGender(request.getUserDetails().getGender());
			user.setUserDetails(userDetails);

			UserEducation userEducation = new UserEducation();
			userEducation.setUser(user);
			userEducation.setCgpa(Float.valueOf(request.getUserEducationBean().getCgpa()));
			userEducation.setHscBoardName(request.getUserEducationBean().getHscBoardName());
			userEducation.setHscPercentage(Float.valueOf(request.getUserEducationBean().getHscPercentage()));
			userEducation.setSscBoardName(request.getUserEducationBean().getSscBoardName());
			userEducation.setSscPercentage(Float.valueOf(request.getUserEducationBean().getSscPercentage()));
			userEducation.setUniversityName(request.getUserEducationBean().getUniversityName());
			user.setUserEducation(userEducation);

			List<UserEmploymentBean> uEmpBean = request.getUserEmploymentBean();
			List<UserEmployment> empList = new ArrayList<>();
			for(UserEmploymentBean empBean : uEmpBean) {
				UserEmployment userEmp = new UserEmployment();
				userEmp.setUser(user);
				userEmp.setCompanyLocation(empBean.getCompanyLocation());
				userEmp.setCompanyName(empBean.getCompanyName());
				userEmp.setEndDate(empBean.getEndDate());
				userEmp.setStartDate(empBean.getStartDate());
				userEmp.setTechnology(empBean.getTechnology());
				empList.add(userEmp);

			}
			user.setUserEmployment(empList);

			User user1 = this.userRepo.save(user);

			if(user1 != null) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS," User saved" );
			} else
				errorMsg = "No Records found for requested input.";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
			e.printStackTrace();
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting addUser() from UserServiceImpl with return:: returnValue: "+returnValue);
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
			List<UserBean> userList = new ArrayList<>();

			if(!(userDetailsList.isEmpty())) {

				for(User user :userDetailsList) {
					UserBean userBean = new UserBean();

					if(user.getUserName() != null)
						userBean.setUserName(user.getUserName());
					if(user.getPassword() != null)
						userBean.setUserPassword(user.getPassword());

					UserDetailsBean userDetailsBean = new UserDetailsBean();
					if(user.getUserDetails().getFirstName() != null)
						userDetailsBean.setFirstName(user.getUserDetails().getFirstName());
					if(user.getUserDetails().getLastName() != null)
						userDetailsBean.setLastName(user.getUserDetails().getLastName());
					if(user.getUserDetails().getEmail() != null)
						userDetailsBean.setEmail(user.getUserDetails().getEmail());
					if(user.getUserDetails().getGender() != null)
						userDetailsBean.setGender(user.getUserDetails().getGender());
					userBean.setUserDetails(userDetailsBean);

					UserEducationBean userEducationBean = new UserEducationBean();

					if(user.getUserEducation() != null) {
						if(user.getUserEducation().getCgpa() != null)
							userEducationBean.setCgpa((user.getUserEducation().getCgpa()).toString());
						if(user.getUserEducation().getHscBoardName() != null)
							userEducationBean.setHscBoardName(user.getUserEducation().getHscBoardName());
						if(user.getUserEducation().getHscPercentage() != null)
							userEducationBean.setHscPercentage(user.getUserEducation().getHscPercentage().toString());
						if(user.getUserEducation().getSscBoardName() != null)
							userEducationBean.setSscBoardName(user.getUserEducation().getSscBoardName());
						if(user.getUserEducation().getSscPercentage() != null)
							userEducationBean.setSscPercentage(user.getUserEducation().getSscPercentage().toString());
						if(user.getUserEducation().getUniversityName() != null)
							userEducationBean.setUniversityName(user.getUserEducation().getUniversityName());
					}
					userBean.setUserEducationBean(userEducationBean);

					List<UserEmployment> userEmployment = user.getUserEmployment();
					
					List<UserEmploymentBean> userEmploymentList = new ArrayList<>();

					if(userEmployment != null ) {
						for(UserEmployment uEmp : userEmployment) {
							UserEmploymentBean userEmploymentBean = new UserEmploymentBean();
							if(uEmp.getCompanyLocation() != null)
								userEmploymentBean.setCompanyLocation(uEmp.getCompanyLocation());
							if(uEmp.getCompanyName() != null)
								userEmploymentBean.setCompanyName(uEmp.getCompanyName());
							if(uEmp.getEndDate() != null)
								userEmploymentBean.setEndDate(uEmp.getEndDate());
							if(uEmp.getStartDate() != null)
								userEmploymentBean.setStartDate(uEmp.getStartDate());
							if(uEmp.getTechnology() != null)
								userEmploymentBean.setTechnology(uEmp.getTechnology());
							userEmploymentList.add(userEmploymentBean);
						}
						userBean.setUserEmploymentBean(userEmploymentList);
					}
					userList.add(userBean);
				}
			}

			LOGGER.trace("USER_DETAILS_LIST:: "+userList);
			if(!userDetailsList.isEmpty()) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS, userList);
			} else
				errorMsg = "No Records found for requested input.";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+ e.getStackTrace());
			e.printStackTrace();
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
			Optional<User> user = userRepo.findById(id);

			if(user.isPresent()) {
				user.get().setActive(false);
				userRepo.save(user.get());
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS, "User Deleted");
			} else
				errorMsg = "No User found for this ID";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
			e.printStackTrace();
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
			List<UserBean> userList = new ArrayList<>();

			if(!(userDetailsList.isEmpty())) {

				for(User user :userDetailsList) {
					UserBean userBean = new UserBean();

					if(user.getUserName() != null)
						userBean.setUserName(user.getUserName());
					if(user.getPassword() != null)
						userBean.setUserPassword(user.getPassword());

					UserDetailsBean userDetailsBean = new UserDetailsBean();
					if(user.getUserDetails().getFirstName() != null)
						userDetailsBean.setFirstName(user.getUserDetails().getFirstName());
					if(user.getUserDetails().getLastName() != null)
						userDetailsBean.setLastName(user.getUserDetails().getLastName());
					if(user.getUserDetails().getEmail() != null)
						userDetailsBean.setEmail(user.getUserDetails().getEmail());
					if(user.getUserDetails().getGender() != null)
						userDetailsBean.setGender(user.getUserDetails().getGender());
					userBean.setUserDetails(userDetailsBean);

					UserEducationBean userEducationBean = new UserEducationBean();

					if(user.getUserEducation() != null) {
						if(user.getUserEducation().getCgpa() != null)
							userEducationBean.setCgpa((user.getUserEducation().getCgpa()).toString());
						if(user.getUserEducation().getHscBoardName() != null)
							userEducationBean.setHscBoardName(user.getUserEducation().getHscBoardName());
						if(user.getUserEducation().getHscPercentage() != null)
							userEducationBean.setHscPercentage(user.getUserEducation().getHscPercentage().toString());
						if(user.getUserEducation().getSscBoardName() != null)
							userEducationBean.setSscBoardName(user.getUserEducation().getSscBoardName());
						if(user.getUserEducation().getSscPercentage() != null)
							userEducationBean.setSscPercentage(user.getUserEducation().getSscPercentage().toString());
						if(user.getUserEducation().getUniversityName() != null)
							userEducationBean.setUniversityName(user.getUserEducation().getUniversityName());
					}
					userBean.setUserEducationBean(userEducationBean);

					List<UserEmployment> userEmployment = user.getUserEmployment();
					List<UserEmploymentBean> userEmploymentList = new ArrayList<>();

					if(userEmployment != null ) {
						for(UserEmployment uEmp : userEmployment) {
							UserEmploymentBean userEmploymentBean = new UserEmploymentBean();
							if(uEmp.getCompanyLocation() != null)
								userEmploymentBean.setCompanyLocation(uEmp.getCompanyLocation());
							if(uEmp.getCompanyName() != null)
								userEmploymentBean.setCompanyName(uEmp.getCompanyName());
							if(uEmp.getEndDate() != null)
								userEmploymentBean.setEndDate(uEmp.getEndDate());
							if(uEmp.getStartDate() != null)
								userEmploymentBean.setStartDate(uEmp.getStartDate());
							if(uEmp.getTechnology() != null)
								userEmploymentBean.setTechnology(uEmp.getTechnology());
							userEmploymentList.add(userEmploymentBean);
						}
						userBean.setUserEmploymentBean(userEmploymentList);
					}
					userList.add(userBean);
				}
			}
			LOGGER.trace("USER_DETAILS_LIST:: "+userList);
			if(!userDetailsList.isEmpty()) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData(USER_DETAILS, userList);
			} else
				errorMsg = "No Records found for requested input.";

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+ e.getStackTrace());
			e.printStackTrace();
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
			List<UserBean> userList = new ArrayList<>();

			if(!(userDetailsList.isEmpty())) {

				for(User user :userDetailsList) {
					UserBean userBean = new UserBean();

					if(user.getUserName() != null)
						userBean.setUserName(user.getUserName());
					if(user.getPassword() != null)
						userBean.setUserPassword(user.getPassword());

					UserDetailsBean userDetailsBean = new UserDetailsBean();
					if(user.getUserDetails().getFirstName() != null)
						userDetailsBean.setFirstName(user.getUserDetails().getFirstName());
					if(user.getUserDetails().getLastName() != null)
						userDetailsBean.setLastName(user.getUserDetails().getLastName());
					if(user.getUserDetails().getEmail() != null)
						userDetailsBean.setEmail(user.getUserDetails().getEmail());
					if(user.getUserDetails().getGender() != null)
						userDetailsBean.setGender(user.getUserDetails().getGender());
					userBean.setUserDetails(userDetailsBean);

					UserEducationBean userEducationBean = new UserEducationBean();

					if(user.getUserEducation() != null) {
						if(user.getUserEducation().getCgpa() != null)
							userEducationBean.setCgpa((user.getUserEducation().getCgpa()).toString());
						if(user.getUserEducation().getHscBoardName() != null)
							userEducationBean.setHscBoardName(user.getUserEducation().getHscBoardName());
						if(user.getUserEducation().getHscPercentage() != null)
							userEducationBean.setHscPercentage(user.getUserEducation().getHscPercentage().toString());
						if(user.getUserEducation().getSscBoardName() != null)
							userEducationBean.setSscBoardName(user.getUserEducation().getSscBoardName());
						if(user.getUserEducation().getSscPercentage() != null)
							userEducationBean.setSscPercentage(user.getUserEducation().getSscPercentage().toString());
						if(user.getUserEducation().getUniversityName() != null)
							userEducationBean.setUniversityName(user.getUserEducation().getUniversityName());
					}
					userBean.setUserEducationBean(userEducationBean);

					List<UserEmployment> userEmployment = user.getUserEmployment();
					
					List<UserEmploymentBean> userEmploymentList = new ArrayList<>();

					if(userEmployment != null ) {
						for(UserEmployment uEmp : userEmployment) {
							UserEmploymentBean userEmploymentBean = new UserEmploymentBean();
							if(uEmp.getCompanyLocation() != null)
								userEmploymentBean.setCompanyLocation(uEmp.getCompanyLocation());
							if(uEmp.getCompanyName() != null)
								userEmploymentBean.setCompanyName(uEmp.getCompanyName());
							if(uEmp.getEndDate() != null)
								userEmploymentBean.setEndDate(uEmp.getEndDate());
							if(uEmp.getStartDate() != null)
								userEmploymentBean.setStartDate(uEmp.getStartDate());
							if(uEmp.getTechnology() != null)
								userEmploymentBean.setTechnology(uEmp.getTechnology());
							userEmploymentList.add(userEmploymentBean);
						}
						userBean.setUserEmploymentBean(userEmploymentList);
					}
					userList.add(userBean);
				}
				if(!userDetailsList.isEmpty()) {
					dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
					dashboardResponse.setResponseData(USER_DETAILS, userList);
				} 
			} else {
				errorMsg = "No Records found for requested input.";
			}

		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+ e.getStackTrace());
			e.printStackTrace();
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
	public String editUser(String dashboardRequest, int id) throws Exception {
		LOGGER.trace("Starting editUser() from UserServiceImpl with arguments:: dashboardRequest: "+dashboardRequest);
		String returnValue = null;
		String errorMsg = null;
		DashboardResponse dashboardResponse = new DashboardResponse();
		try {
			Optional<User> u = this.userRepo.findById(id);

			if(u.isPresent()) {

				UserBean request = MAPPER.readValue(dashboardRequest, UserBean.class);
				System.out.println(request);
				User user = u.get();
				user.setActive(true);
				user.setUserName(request.getUserName() != null ? request.getUserName() : user.getUserName());
				user.setPassword(request.getUserPassword() != null ? request.getUserPassword() : user.getPassword());

				UserDetails userDetails = user.getUserDetails();
				
				if(request.getUserDetails() != null) {
				userDetails.setFirstName(request.getUserDetails().getFirstName() != null ? request.getUserDetails().getFirstName() : user.getUserDetails().getFirstName());
				userDetails.setLastName(request.getUserDetails().getLastName() != null ? request.getUserDetails().getLastName() : user.getUserDetails().getLastName());
				//userDetails.setAddress(request.getUserDetails().getAddress());
				userDetails.setEmail(request.getUserDetails().getEmail() != null ? request.getUserDetails().getEmail() : user.getUserDetails().getEmail());
				userDetails.setGender(request.getUserDetails().getGender() != null ? request.getUserDetails().getGender() : user.getUserDetails().getGender());
				user.setUserDetails(userDetails);
				} 
				UserEducation userEducation = user.getUserEducation();
				if(request.getUserEducationBean() != null) {
				userEducation.setCgpa(request.getUserEducationBean().getCgpa() != null ? Float.valueOf(request.getUserEducationBean().getCgpa()) : user.getUserEducation().getCgpa());
				userEducation.setHscBoardName(request.getUserEducationBean().getHscBoardName() != null ? request.getUserEducationBean().getHscBoardName() : user.getUserEducation().getHscBoardName());
				userEducation.setHscPercentage(request.getUserEducationBean().getHscPercentage() != null ? Float.valueOf(request.getUserEducationBean().getHscPercentage()) : user.getUserEducation().getHscPercentage());
				userEducation.setSscBoardName(request.getUserEducationBean().getSscBoardName() != null ? request.getUserEducationBean().getSscBoardName() : user.getUserEducation().getSscBoardName());
				userEducation.setSscPercentage(request.getUserEducationBean().getSscPercentage() != null ? Float.valueOf(request.getUserEducationBean().getSscPercentage()) : user.getUserEducation().getSscPercentage());
				userEducation.setUniversityName(request.getUserEducationBean().getUniversityName() != null ? request.getUserEducationBean().getUniversityName() : user.getUserEducation().getUniversityName());
				user.setUserEducation(userEducation);
				}

				List<UserEmployment> userEmpmt = user.getUserEmployment();
				List<UserEmploymentBean> uEmpBean = request.getUserEmploymentBean();
				
				if(uEmpBean != null ) {
				List<UserEmployment> empList = new ArrayList<>();
				for(UserEmploymentBean empBean : uEmpBean) {
					for(UserEmployment userEmp : userEmpmt) {
						userEmp.setCompanyLocation(empBean.getCompanyLocation() != null ? empBean.getCompanyLocation() : userEmp.getCompanyLocation());
						userEmp.setCompanyName(empBean.getCompanyName() != null ? empBean.getCompanyName() : userEmp.getCompanyName());
						userEmp.setEndDate(empBean.getEndDate() != null ? empBean.getEndDate() : userEmp.getEndDate());
						userEmp.setStartDate(empBean.getStartDate() != null ? empBean.getStartDate() : userEmp.getStartDate());
						userEmp.setTechnology(empBean.getTechnology() != null ? empBean.getTechnology() : userEmp.getTechnology());
						empList.add(userEmp);
					}
				}
				user.setUserEmployment(empList);
				}
				User user1 = this.userRepo.save(user);

				if(user1 != null) {
					dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
					dashboardResponse.setResponseData(USER_DETAILS," Changes saved" );
				}
			} else {
				errorMsg = "No Records found for requested input.";
			}
		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
			e.printStackTrace();
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting editUser() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}

}

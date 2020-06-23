package com.neo.spring.demo.service;

import com.neo.spring.demo.model.User;

public interface UserService {

	public String addUser(String dashboardRequest) throws Exception;

	public String getAllActiveUsers() throws Exception;

	public User editUser(User user);

	public String deleteUser(int id) throws Exception;

	public String softDelete(int id) throws Exception;

	public String getAllUsers() throws Exception;

	public String searchByEmail(String email) throws Exception;

}

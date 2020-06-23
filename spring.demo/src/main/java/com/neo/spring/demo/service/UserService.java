package com.neo.spring.demo.service;

public interface UserService {

	public String addUser(String dashboardRequest) throws Exception;

	public String getAllActiveUsers() throws Exception;

	public String editUser(String user, int id) throws Exception;

	public String deleteUser(int id) throws Exception;

	public String softDelete(int id) throws Exception;

	public String getAllUsers() throws Exception;

	public String searchByEmail(String email) throws Exception;

}

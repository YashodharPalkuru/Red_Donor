package com.reddonor.services;

import java.sql.SQLException;

import com.reddonor.entity.LoginBean;
import com.reddonor.entity.UserBean;

public interface RedDonorService {
	//public void register(long drMobile, int drPswd, String drEmail, int drciId, int drArId, int drCoId, int drBgId, String drFirstName, String drLastName);
	public void isAvailable(int drMobile);
	//public LoginBean login(int drMobile,String drPswd) throws SQLException;
	public String generateCsrfToken(String username, String drPswd);
	public void isValidSession(String session);
	public LoginBean loginFB();
	public boolean register(String fname, String lname, String email,String password) throws Exception;
	public UserBean getUserDetails(String userId);
	public LoginBean login(String username, String drPswd) throws SQLException;
	public boolean isEmailAvailable(String email) throws SQLException;
}

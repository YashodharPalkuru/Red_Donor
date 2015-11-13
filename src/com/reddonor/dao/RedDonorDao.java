package com.reddonor.dao;

import java.sql.SQLException;

import com.reddonor.entity.LoginBean;
import com.reddonor.entity.UserBean;

public interface RedDonorDao
{
    //public void register(int drMobile, int drPswd, String drEmail, int drciId, int drArId, int drCoId, int drBgId, String drFirstName, String drLastName);
    public void isAvailable(int drMobile);
    public boolean login(String username, String drPswd) throws SQLException;
    public void insertCsrfToken(String username, String drPswd, String csrfToken) throws SQLException;
	public UserBean getUserDetails(String userId) throws SQLException;
	public boolean register(String fname, String lname, String email,String password) throws SQLException;
	public boolean isEmailAvailable(String email) throws SQLException;
	

}

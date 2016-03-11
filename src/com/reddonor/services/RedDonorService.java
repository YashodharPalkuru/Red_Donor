package com.reddonor.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.reddonor.entity.DonorsList;
import com.reddonor.entity.LoginBean;
import com.reddonor.entity.UserBean;
import com.restfb.json.JsonArray;

public interface RedDonorService {
	//public void register(long drMobile, int drPswd, String drEmail, int drciId, int drArId, int drCoId, int drBgId, String drFirstName, String drLastName);
	public void isAvailable(int drMobile);
	//public LoginBean login(int drMobile,String drPswd) throws SQLException;
	public String generateCsrfToken(String username, String drPswd);
	public void isValidSession(String session);
	public LoginBean loginFB();
	public LoginBean register(String fname, String lname, String email,String password,String fbId) throws Exception;
	public UserBean getUserDetails(String userId);
	public LoginBean login(String username, String drPswd) throws SQLException;
	public boolean isEmailAvailable(String email) throws SQLException;
	public JSONArray getDonorList(String bloodGroup, String state, String district,
			String subDistrict) throws SQLException;
	public JSONArray getStatesList(String country)throws SQLException;
	public JSONArray getDistrictsList(String state)throws SQLException;
	public JSONArray getSubDistrictsList(String district)throws SQLException;
	public UserBean registerDetails(String bloodGroup, String mobile,
			String state, String district, String subDistrict, String usrID, String facebookId) throws SQLException;
}

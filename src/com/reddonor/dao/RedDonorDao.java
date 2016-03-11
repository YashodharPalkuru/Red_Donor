package com.reddonor.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.reddonor.entity.LoginBean;
import com.reddonor.entity.UserBean;
import com.restfb.json.JsonArray;

public interface RedDonorDao
{
    //public void register(int drMobile, int drPswd, String drEmail, int drciId, int drArId, int drCoId, int drBgId, String drFirstName, String drLastName);
    public void isAvailable(int drMobile);
    public boolean login(String username, String drPswd) throws SQLException;
    public void insertCsrfToken(String username, String drPswd, String csrfToken) throws SQLException;
	public UserBean getUserDetails(String userId) throws SQLException;
	public LoginBean register(String fname, String lname, String email,String password,String fbId) throws SQLException;
	public boolean isEmailAvailable(String email) throws SQLException;
	public JSONArray getDonorList(String bloodGroup, String state, String district,
			String subDistrict) throws SQLException;
	public JSONArray getStatesList(String country)throws SQLException;
	JSONArray getSubDistrictsList(String district) throws SQLException;
	JSONArray getDistrictsList(String state) throws SQLException;
	public UserBean registerDetails(String bloodGroup, String mobile,
			String state, String district, String subDistrict,String usrID, String facebookId) throws SQLException;
	

}

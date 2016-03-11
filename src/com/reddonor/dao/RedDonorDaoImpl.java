package com.reddonor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.reddonor.db.DbManager;
import com.reddonor.entity.Address;
import com.reddonor.entity.DonorsList;
import com.reddonor.entity.LoginBean;
import com.reddonor.entity.UserBean;
import com.restfb.json.JsonArray;



public class RedDonorDaoImpl implements RedDonorDao{

	/*@Override
	public void register(int drMobile, int drPswd, String drEmail, int drciId,
			int drArId, int drCoId, int drBgId, String drFirstName,
			String drLastName) {
		
	}*/

	@Override
	public void isAvailable(int drMobile) {
		
	}

	@Override
	public boolean login(String username, String drPswd) throws SQLException
	{
		
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try
        {
        conn = DbManager.getConnection();
        ps = conn.prepareStatement("select count(*) from usr_login where (usr_log_mobile = ? or usr_log_email = ?) and usr_pswd = ?");
        ps.setString(1, username);
        ps.setString(2, username);
        ps.setString(3, drPswd);
        rs = ps.executeQuery();
        if(rs.next())
        {
            if(rs.getInt(1) > 0)
            {
            	return Boolean.TRUE;
            }
            else
            {
            	return Boolean.FALSE;
            }
        }
        }
        catch(SQLException e)
        {
            throw new SQLException(e);	
        }
		return Boolean.FALSE;
	}

	@Override
	public void insertCsrfToken(String username, String drPswd, String csrfToken) throws SQLException
	{
        PreparedStatement ps = null;
        Connection conn = null;
        //TODO: we need to handle mobile number and email also now this could throw error for email as user name
       // double mobile = Double.parseDouble(username.substring(0, 9));

        conn = DbManager.getConnection();
        ps = conn.prepareStatement("update usr_login SET usr_csrf_token = ? WHERE (usr_log_mobile = ? OR usr_log_email = ?) AND (usr_pswd = ?)");
        ps.setString(1, csrfToken);
        ps.setString(2, username);
        ps.setString(3, username);
        ps.setString(4, drPswd);
        
        if(ps.executeUpdate() > 0)
        {
        	System.out.println("csrf token inserted");
        }
        else
        {
        	throw new SQLException();
        }
       
	}

	@Override
	public UserBean getUserDetails(String userId) {
		// TODO Auto-generated method stub
		
		UserBean user = new UserBean();
		Address address = new Address();
		PreparedStatement ps = null;
        Connection conn = null;
        try {
		
		conn = DbManager.getConnection();
		
		ps = conn.prepareStatement("SELECT usr_id, usr_facebook_id,usr_firstname,usr_lastname,usr_email,usr_mobile,bloodgroup_name,country_name,state_name,district_name,subdistrict_name FROM usr_registration_tbl LEFT JOIN  bloodgroup ON usr_bloodgroup_id = bloodgroup_id LEFT JOIN countries ON usr_country_id = country_id LEFT JOIN states ON usr_state_id = state_id LEFT JOIN districts ON usr_district_id = district_id LEFT JOIN subdistricts ON usr_subdistrict_id = subdistrict_id WHERE (usr_email = ? OR usr_mobile = ? OR usr_facebook_id = ?)");
        
			/*ps = conn.prepareStatement("SELECT usr_id, usr_facebook_id,usr_firstname,usr_lastname,usr_email,usr_mobile,bloodgroup_name,country_name,state_name,district_name,subdistrict_name"
					+ "FROM usr_registration_tbl LEFT JOIN  bloodgroup ON usr_bloodgroup_id = bloodgroup_id LEFT JOIN countries ON usr_country_id = country_id LEFT JOIN states ON usr_state_id = state_id LEFT JOIN districts ON usr_district_id = district_id LEFT JOIN subdistricts ON usr_subdistrict_id = subdistrict_id"
					+ " WHERE (usr_email = ? OR usr_mobile = ? OR usr_facebook_id = ?)");*/
		
        ps.setString(1, userId);
        ps.setString(2, userId);
        ps.setString(3, userId);
        ResultSet rs= ps.executeQuery();
	    while(rs.next()) {
	    	user.setId(rs.getInt(1));
	    	user.setFacebookId(rs.getString(2));
	    	user.setfName(rs.getString(3));
	    	user.setlName(rs.getString(4));
	    	user.setEmail(rs.getString(5));
	    	user.setMobile(rs.getLong(6));
	    	user.setBloodGroup(rs.getString(7));
	    	address.setCountry(rs.getString(8));
	    	address.setState(rs.getString(9));
	    	address.setCity(rs.getString(10));
	    	address.setMandal(rs.getString(11));
	    	//address.setPinCode(rs.getInt(12));
	    	user.setAddress(address);
	    	System.out.println(user);
	    }  
				
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
			return user;
	        
	       
		
	}

	@Override
	public LoginBean register(String fname, String lname, String email,
		String password,String fbId) throws SQLException {
		Boolean rs1 = null;
		Boolean rs2 = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try
        {
        conn = DbManager.getConnection();
        ps = conn.prepareStatement("INSERT INTO usr_login (usr_log_email, usr_pswd) VALUES (?,?)");
        ps.setString(1, email);
        ps.setString(2, password);
        int a = ps.executeUpdate();
        ps = conn.prepareStatement("INSERT INTO usr_registration_tbl (usr_firstname, usr_lastname, usr_email,usr_facebook_id) VALUES (?,?,?,?)");
        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setString(3, email);
        ps.setString(4, fbId);
        int b = ps.executeUpdate();
        LoginBean login = new LoginBean();
        if(a > 0 && b > 0)
        {
        	ps = conn.prepareStatement("SELECT usr_id,usr_facebook_id,usr_csrf_token from usr_registration_tbl LEFT Join usr_login ON usr_id = usr_log_id WHERE (usr_log_email = ? AND usr_pswd = ?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
           // String usrID ="";
            while(rs.next()){
            	login.setUserId(rs.getString("usr_id"));
            	login.setCsrfToken("usr_csrf_token");
            	login.setStatus(Boolean.TRUE);
            	login.setFbId("usr_facebook_id");
            }
           //user = this.getUserDetails(usrID);
            return login;
           
         }
         else
            {
            	return login;
            }
        
        }
        catch(SQLException e)
        {
            throw new SQLException(e);	
        }
		//return Boolean.FALSE;
	}

	@Override
	public boolean isEmailAvailable(String email) throws SQLException {
		PreparedStatement ps = null;
        Connection conn = null;

        conn = DbManager.getConnection();
        ps = conn.prepareStatement("select * from usr_login where  usr_log_email = ? ");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
      
        
        if(rs.next())
        {
        	return true;
        }
        else
        {
        	return false;
        }
		
	}

	@Override
	public JSONArray getDonorList(String bloodGroup, String state,
			String district, String subDistrict) throws SQLException {
		System.out.println("getdonor list");
		    ResultSet rs = null;
	        PreparedStatement ps = null;
	        Connection conn = null;
	        List<DonorsList> rows;
	        JSONObject result = new JSONObject();
	        JSONArray array = new JSONArray();
	        DonorsList row;
	        try
	        {
	        conn = DbManager.getConnection();
	        ps = conn.prepareStatement("select usr_firstname,bloodgroup_name,usr_log_mobile, usr_facebook_id,subdistrict_name from"
	        		+ " usr_registration_tbl left join bloodgroup on usr_registration_tbl.usr_bloodgroup_id = bloodgroup.bloodgroup_id "
	        		+ "left join usr_login on usr_registration_tbl.usr_id = usr_login.usr_log_id left join subdistricts "
	        		+ "on usr_registration_tbl.usr_subdistrict_id = subdistricts.subdistrict_id WHERE (bloodgroup_name = ? )"
	        		+ " AND(select COUNT(*) FROM states WHERE state_name= ? )>0 AND (select COUNT(*) FROM districts WHERE district_name= ? )>0 "
	        		+ "AND (SELECT COUNT(*) FROM subdistricts WHERE subdistrict_name = ? )>0 ");
	        ps.setString(1, bloodGroup);
	        ps.setString(2, state);
	        ps.setString(3, district);
	        ps.setString(4, subDistrict);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            JSONArray ja = new JSONArray();
	            ja.add(rs.getString("usr_firstname"));
	            ja.add(rs.getString("bloodgroup_name"));
	            ja.add(rs.getString("usr_log_mobile"));
	            ja.add(rs.getString("usr_facebook_id"));
	            ja.add(rs.getString("subdistrict_name"));
	            array.add(ja);
	        }
	        
	        
	        /*int size = rs.getFetchSize();
	        rows = new ArrayList<DonorsList>(size);
	        while(rs.next()){
	        	row = new DonorsList();
	        	row.setName(rs.getString(0));
	        	row.setBloodGroup(rs.getString(1));
	        	row.setMobile(Long.parseLong(rs.getString(2)));
	        	row.setFacebookId(rs.getString(3));
	        	row.setSubdistrict_name(rs.getString(4));
	        	rows.add(row);
	       
	       
	        }*/
	        
		
		
		return array;
	}
	        catch(SQLException e)
	        {
	            throw new SQLException(e);	
	        }
	}

	@Override
	public JSONArray getStatesList(String country) throws SQLException {
		PreparedStatement ps = null;
        Connection conn = null;
        JSONArray ja = new JSONArray();

        conn = DbManager.getConnection();
        ps = conn.prepareStatement("select state_name from states where (select country_id from countries where country_name = ?) = states.state_country_id ");
        ps.setString(1, country);
        ResultSet rs = ps.executeQuery();
      
        
        while(rs.next())
        {
         JSONObject jo = new JSONObject();
         jo.put("state",rs.getString("state_name"));
         ja.add(jo);
        }
       return ja;
	}
	@Override
	public JSONArray getDistrictsList(String state) throws SQLException {
		PreparedStatement ps = null;
        Connection conn = null;
        JSONArray ja = new JSONArray();

        conn = DbManager.getConnection();
        ps = conn.prepareStatement("select district_name from districts where (select state_id from states where state_name = ?) = districts.district_state_id");
        ps.setString(1, state);
        ResultSet rs = ps.executeQuery();
      
        
        while(rs.next())
        {
         JSONObject jo = new JSONObject();
         jo.put("district",rs.getString("district_name"));
         ja.add(jo);
        }
       return ja;
	}
	
	@Override
	public JSONArray getSubDistrictsList(String district) throws SQLException {
		PreparedStatement ps = null;
        Connection conn = null;
        JSONArray ja = new JSONArray();

        conn = DbManager.getConnection();
        ps = conn.prepareStatement("select subdistrict_name from subdistricts where (select district_id from districts where district_name = ?) = subdistricts.subdistrict_district_id ");
        ps.setString(1, district);
        ResultSet rs = ps.executeQuery();
      
        
        while(rs.next())
        {
         JSONObject jo = new JSONObject();
         jo.put("subDistrict",rs.getString("subdistrict_name"));
         ja.add(jo);
        }
       return ja;
	}

	@Override
	public UserBean registerDetails(String bloodGroup, String mobile,
			String state, String district, String subDistrict, String usrID, String facebookId) throws SQLException {
		Boolean rs1 = null;
		Boolean rs2 = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try
        {
        conn = DbManager.getConnection();
        ps = conn.prepareStatement("UPDATE  usr_login SET usr_log_mobile = ? WHERE usr_log_id = ? OR usr_log_id = (select usr_id from usr_registration_tbl where usr_facebook_id =?)");
        ps.setString(1, mobile);
        ps.setString(2, usrID);
        ps.setString(3, facebookId);
        
        int a = ps.executeUpdate();
        ps = conn.prepareStatement("UPDATE usr_registration_tbl SET usr_bloodgroup_id = (select bloodgroup_id from bloodgroup where bloodgroup_name = ?) , usr_mobile = ?, usr_state_id =(select state_id from states where state_name = ?), usr_district_id = (select district_id from districts where district_name = ?), usr_subdistrict_id = (select subdistrict_id from subdistricts where subdistrict_name = ?) WHERE usr_registration_tbl.usr_id = ? OR usr_facebook_id =?");
        ps.setString(1, bloodGroup);
        ps.setString(2, mobile);
        ps.setString(3, state);
        ps.setString(4, district);
        ps.setString(5, subDistrict);
        ps.setString(6, usrID);
        ps.setString(7, facebookId);

        
       
        int b = ps.executeUpdate();
        UserBean user = new UserBean();
        if(a > 0 && b > 0)
        {
        	if(usrID != null || !(usrID.equals(""))){
        		
        		user =  this.getUserDetails(usrID);
        	}
        	else 
        	{
        		user =  this.getUserDetails(facebookId);
        	}
        		
           return user;
         }
         else
            {
            	return user;
            }
        
        }
        catch(SQLException e)
        {
            throw new SQLException(e);	
        }
	
	}
    
}

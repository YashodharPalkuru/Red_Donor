package com.reddonor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.reddonor.db.DbManager;
import com.reddonor.entity.Address;
import com.reddonor.entity.LoginBean;
import com.reddonor.entity.UserBean;



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
        ps = conn.prepareStatement("select count(*) from donor_login where (dl_mobile = ? or dl_email = ?) and dl_pswd = ?");
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

        conn = DbManager.getConnection();
        ps = conn.prepareStatement("update donor_login set dl_csrf_token = ? where (dl_mobile = ? or dl_email = ?) and dl_pswd = ?");
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
        
			ps = conn.prepareStatement("select dr_id, fb_id,dr_firstname,dr_lastname,dr_email,dr_mobile,bg_name,co_name,st_name,ci_name,ar_name,ar_pincode "
					+ "from donor_register left join  bloodgroup on dr_bg_id = bg_id left join country on dr_co_id = co_id left join state on dr_st_id = st_id left join city on dr_ci_id = ci_id left join region on dr_ar_id = ar_id"
					+ " where (dr_email = ? or dr_mobile = ? or fb_id = ?)");
		
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
	    	address.setPinCode(rs.getInt(12));
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
	public boolean register(String fname, String lname, String email,
		String password) throws SQLException {
		Boolean rs1 = null;
		Boolean rs2 = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try
        {
        conn = DbManager.getConnection();
        ps = conn.prepareStatement("INSERT INTO donor_login (dl_email, dl_pswd) VALUES (?,?)");
        ps.setString(1, email);
        ps.setString(2, password);
        int a = ps.executeUpdate();
        ps = conn.prepareStatement("INSERT INTO donor_register (dr_firstname, dr_lastname, dr_email) VALUES (?,?,?)");
        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setString(3, email);
        int b = ps.executeUpdate();
        if(a > 0 && b > 0)
        {
            return Boolean.TRUE;
         }
         else
            {
            	return Boolean.FALSE;
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
        ps = conn.prepareStatement("select * from donor_login where  dl_email = ? ");
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

	
    
}

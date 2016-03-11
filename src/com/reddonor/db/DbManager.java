package com.reddonor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager 
{
	// Replace it wid hibernate or ibatis
	private static Connection connection;
	private DbManager()
	{
	}
    public static synchronized Connection getConnection() throws SQLException
    {
    	if(connection == null || connection.isClosed())
    	{
    		try 
    		{
				String url = "jdbc:mysql://localhost:3306/RedDonorDB";
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, "root","");
			} 
    		catch (Exception e) 
    		{ 
				e.printStackTrace();
			}
    	}
        return connection;
    }
}
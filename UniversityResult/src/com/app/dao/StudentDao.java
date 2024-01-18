package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.model.Student;

public class StudentDao 
{
	public static Connection getConnection()throws SQLException{
    	Connection con=null;
    	
    	try {
    		
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	    con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db2","root","Dhanashree@1004");
    	}
    	catch(ClassNotFoundException e)
    	{
    		e.printStackTrace();
    	}
     return con;
    }
	
	
    
   
    
    public static boolean validate(Student s)throws SQLException
    {
    	Connection con=StudentDao.getConnection();
    	PreparedStatement stmt=con.prepareStatement("select *from Student where PRN=?");
    	stmt.setString(1,s.getPRN());
    	
    	ResultSet rs=stmt.executeQuery();
    	
    	
    	
    	
    	boolean s1=rs.next();
    	
    	return s1;
    }
}

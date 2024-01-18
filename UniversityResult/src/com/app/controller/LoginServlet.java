package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.StudentDao;
import com.app.model.Student;


@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public LoginServlet() {
       super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String prn=request.getParameter("txtPRN");
		
		
		Student s= new Student();
		
		s.setPRN(prn);
		
		
		
		boolean status=false;
		try {
			status=StudentDao.validate(s);
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		if(status==true)
		{
			pw.write("Login Successfull");
			
			try {
				Connection con=StudentDao.getConnection();
				PreparedStatement stmt=con.prepareStatement("select *from Student where PRN=?and percentage>80");
		    	stmt.setString(1,s.getPRN());
		    	
		    	ResultSet rs=stmt.executeQuery();
		    	boolean ss=rs.next();
		    	pw.write("<h2>University Result</h2>");
		    	pw.write("<br>");
		    	if(ss==true)
		    	{
		    		pw.write("Status : Pass");
		    		pw.write("<br>");
		    		pw.write("Student PRN number :"+rs.getString("PRN"));
		    		pw.write("<br>");
		    		pw.write("Student name:"+rs.getString("name"));
		    		pw.write("<br>");
		    		pw.write("Total Marks:"+rs.getString("msub1"));
		    		pw.write("<br>");
		    		pw.write("Total Marks:"+rs.getString("msub2"));
		    		pw.write("<br>");
		    		pw.write("Total Marks:"+rs.getString("msub3"));
		    		pw.write("<br>");
		    		pw.write("Total Marks:"+rs.getString("total"));
		    		pw.write("<br>");
		    		pw.write("Percentage:"+rs.getString("percentage"));
		    		
		    		
		    	}
		    	
		    	else
		    		pw.write("Status : fail");
		    	
				}catch(SQLException e)
			    {
					e.printStackTrace();
			     }
			 
		}
		else
		{
			pw.write("Sorry Invalid PRN Number or password!!!");
			request.getRequestDispatcher("index.html").include(request,response);
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

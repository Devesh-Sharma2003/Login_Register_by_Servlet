package com.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String mail = req.getParameter("email");
		String pass = req.getParameter("pass");
		String city = req.getParameter("city");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("dob");
		PrintWriter out = resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Devesh@2003");
			PreparedStatement ps = con.prepareStatement("Insert into User values(?,?,?,?,?,?,?)");
			ps.setString(1,id);
			ps.setString(2,name);
			ps.setString(3,mail);
			ps.setString(4,pass);
			ps.setString(5,city);
			ps.setString(6,gender);
			ps.setString(7,dob);
			int rs = ps.executeUpdate();
			if(rs>0)
			{
				out.println("<h3 style='color:green'>** Record Inserted Successfully **</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/register_me.html");
				rd.include(req, resp);
			}
			else
			{
				out.println("<h3 style='color:red'>** Record Failed to Insert **</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/register_me.html");
				rd.include(req, resp);
			}
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}

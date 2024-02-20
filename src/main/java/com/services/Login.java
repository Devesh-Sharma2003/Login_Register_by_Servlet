package com.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();
		String id = req.getParameter("id");
		String pass = req.getParameter("pwd");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try 
			{
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Devesh@2003");
				PreparedStatement ps = con.prepareStatement("select * from User where User_Id=? and User_Password=?");
				ps.setString(1,id);
				ps.setString(2, pass);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					String n = rs.getString("User_Name");
					String m = rs.getString("User_Email");
					String c = rs.getString("User_City");
					String g = rs.getString("User_Gender");
					String b = rs.getString("User_Birth");
					HttpSession session = req.getSession();
					session.setAttribute("names",n);
					session.setAttribute("mails",m);
					session.setAttribute("cities",c);
					session.setAttribute("genders",g);
					session.setAttribute("dobs",b);
					RequestDispatcher rd = req.getRequestDispatcher("/represent.jsp");
					rd.forward(req, resp);	
				}
				else 
				{
					out.println("<h3 style='color:red'> ** No Such User Exist **</h3> ");
					RequestDispatcher rd = req.getRequestDispatcher("/Login.html"); 
					rd.include(req, resp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

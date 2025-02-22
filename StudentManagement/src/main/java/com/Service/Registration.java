package com.Service;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class Registration extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String url = "jdbc:postgresql://localhost:5432/Jspider?user=postgres&password=123";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		Long phone = Long.parseLong(request.getParameter("phone"));
		String password = request.getParameter("password");

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url);

			String sql = "INSERT INTO student (name, age, email, gender, phone, password) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, email);
			ps.setString(4, gender);
			ps.setLong(5, phone);
			ps.setString(6, password);
  
			int i = ps.executeUpdate();
			if (i > 0) {
				out.println("Registration Successful!");
				 RequestDispatcher dispatcher = request.getRequestDispatcher("StudentManagement.html"); 
			        dispatcher.forward(request, response);
			} else {
				out.println("Registration Failed!");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Error: " + e.getMessage() + "</h2>");
		}
	}
}

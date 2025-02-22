package com.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class edit extends HttpServlet {
	
	private static String url = "jdbc:postgresql://localhost:5432/Jspider?user=postgres&password=123";

	static {
		try {
			Class.forName("org.postgresql.Driver");
			
			
		} catch (ClassNotFoundException e) {
			
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String gender = req.getParameter("gender");
		Long phone = Long.parseLong(req.getParameter("phone"));
		String password = req.getParameter("password");

		if (email == null || email.isEmpty()) {
			resp.getWriter().write("Error: Email is required to update record!");
			return;
		}

		System.out.println("Updating record for email: " + email);

		try (Connection connection = DriverManager.getConnection(url);
				PreparedStatement stmt = connection.prepareStatement(
						"UPDATE student SET name = ?, age = ?, gender = ?, phone = ?, password = ? WHERE email = ?")) {

			stmt.setString(1, name);
			stmt.setInt(2, age);
			stmt.setString(3, gender);
			stmt.setLong(4, phone);
			stmt.setString(5, password);
			stmt.setString(6, email);

			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Record updated successfully!");
				
				RequestDispatcher dispatcher=req.getRequestDispatcher("StudentManagement.html");
				dispatcher.forward(req, resp);
			} else {
				System.out.println("No record found to update for email: " + email);
				
				
			}
		} catch (SQLException e) {
			System.err.println("Database error while updating record:");
			e.printStackTrace();
			resp.getWriter().write("Database error: " + e.getMessage());
		}
	}
}
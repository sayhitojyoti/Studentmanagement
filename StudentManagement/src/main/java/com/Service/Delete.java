package com.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static String url = "jdbc:postgresql://localhost:5432/Jspider?user=postgres&password=123";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		if (email != null) {
			try {

				Class.forName("org.postgresql.Driver");
				Connection con = DriverManager.getConnection(url);

				String sql = "DELETE FROM student WHERE email = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				int rowsDeleted = pstmt.executeUpdate();
				pstmt.close();
				con.close();
				if (rowsDeleted > 0) {
					System.out.println("Data Deleted Successfully!");
					RequestDispatcher dispatcher = req.getRequestDispatcher("StudentManagement.html");
					dispatcher.forward(req, resp);
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occured!");
			}
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("DeletePage.html");
		dispatcher.forward(req, resp);
	}
}

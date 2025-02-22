package com.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class DisplayStudent extends HttpServlet {
    private static String url = "jdbc:postgresql://localhost:5432/Jspider?user=postgres&password=123";

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Class.forName("org.postgresql.Driver");

            String sql = "SELECT name, age, email, gender, phone, password FROM student";
            try (Connection con = DriverManager.getConnection(url);
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                out.println("<html>");
                out.println("<head>");
                out.println("<title>Student List</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f0f0f0; text-align: center; }");
                out.println(".container { width: 80%; margin: 20px auto; }");
                out.println("h2 { color: #2a5298; }");
                out.println("table { width: 100%; border-collapse: collapse; background-color: white; }");
                out.println("th, td { padding: 10px; border: 1px solid #ccc; text-align: left; }");
                out.println("th { background-color: #2a5298; color: white; }");
                out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
                out.println(".btn { padding: 5px 10px; text-decoration: none; color: white; border-radius: 4px; }");
                out.println(".btn-edit { background-color: #2a5298; }");
                out.println(".btn-delete { background-color: #d9534f; }");
                out.println(".btn:hover { opacity: 0.8; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h2>Student Details</h2>");

                if (!rs.isBeforeFirst()) {
                    out.println("<p>No student records found.</p>");
                } else {
                    out.println("<table>");
                    out.println("<tr><th>Name</th><th>Age</th><th>Email</th><th>Gender</th><th>Phone</th><th>Password</th><th>Actions</th></tr>");

                    while (rs.next()) {
                        String email = rs.getString("email");
                        out.println("<tr>");
                        out.println("<td>" + rs.getString("name") + "</td>");
                        out.println("<td>" + rs.getInt("age") + "</td>");
                        out.println("<td>" + email + "</td>");
                        out.println("<td>" + rs.getString("gender") + "</td>");
                        out.println("<td>" + rs.getLong("phone") + "</td>");
                        out.println("<td>" + rs.getString("password") + "</td>");
                        out.println("<td>");
                        out.println("<a href='Editpage.html' class='btn btn-edit'>Edit</a> ");
                        out.println("<a href='delete?email=" + email + "' class='btn btn-delete' onclick='return confirm(\"Are you sure?\")'>Delete</a>");
                        out.println("</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                }

                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<p style='color:red;'>Error fetching student data: " + e.getMessage() + "</p>");
        }
    }
}

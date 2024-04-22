import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet 
{

    private static final String URL = "jdbc:postgresql://localhost:5432/pagination";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String seatNo = request.getParameter("seatNo");
        String name = request.getParameter("name");
        String studentClass = request.getParameter("class");
        int totalMarks = Integer.parseInt(request.getParameter("totalMarks"));

        double percentage = (totalMarks / 500.0) * 100;

        Connection conn = null;
        PreparedStatement stmt = null;
        try 
		{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM students WHERE seatNo = ?");
            checkStmt.setInt(1, Integer.parseInt(seatNo));
            ResultSet resultSet = checkStmt.executeQuery();
            if (resultSet.next()) 
			{
                out.println("<html><head><title>Error</title><style>h2 {color: red;}</style></head><body><h2>Someone is already in this seat.</h2></body></html>");
                return;
            }
            stmt = conn.prepareStatement("INSERT INTO students (seatNo, name, class, totalMarks, percentage) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, Integer.parseInt(seatNo));
            stmt.setString(2, name);
            stmt.setString(3, studentClass);
            stmt.setInt(4, totalMarks);
            stmt.setDouble(5, percentage);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) 
			{
               out.println("<html><head><title>Success</title><style>h2 {color: green;}</style></head><body>");
				out.println("<h2>Student details uploaded successfully.</h2>");
				out.println("<form action='list' method='get'>");
				out.println("<input type='submit' value='View Students'>");
				out.println("</form>");
				out.println("</body></html>");
            }
        } 
		catch (SQLException | ClassNotFoundException e) 
		{
            out.println("<html><head><title>Error</title><style>h2 {color: red;}</style></head><body><h2>Error: " + e.getMessage() + "</h2></body></html>");
        } 
		finally 
		{
            try 
			{
                if (stmt != null) 
				{
                    stmt.close();
                }
                if (conn != null) 
				{
                    conn.close();
                }
            } 
			catch (SQLException e) 
			{
                e.printStackTrace();
            }
        }
    }
}

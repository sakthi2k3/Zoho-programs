import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentDisplayServlet extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 5;
    private static final String URL = "jdbc:postgresql://localhost:5432/pagination";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int page = 1;
        if (request.getParameter("page") != null) 
		{
            page = Integer.parseInt(request.getParameter("page"));
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try 
		{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM students ORDER BY seatNo LIMIT ? OFFSET ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, RECORDS_PER_PAGE);
            stmt.setInt(2, (page - 1) * RECORDS_PER_PAGE);
            rs = stmt.executeQuery();

            out.println("<html><head><title>Student Details</title>");
            out.println("<style>");
            out.println(".container { display: flex; justify-content: center; align-items: center; height: 80vh; }");
            out.println(".box { background-color: #fff; border-radius: 10px; padding: 20px; box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1); }");
            out.println("button { background-color: #007bff; color: white; border: none; border-radius: 5px; padding: 10px 20px; margin: 5px; cursor: pointer; transition: background-color 0.3s ease; }");
            out.println("table { width: 80%; margin: auto; border-collapse: collapse; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("a { text-decoration: none; margin-left:20%;}");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class=\"container\">");
            out.println("<div class=\"box\">");

            out.println("<h2>Student Details</h2>");
            out.println("<table>");
            out.println("<tr><th>Seat No</th><th>Name</th><th>Class</th><th>Total Marks</th><th>Percentage</th></tr>");

            while (rs.next()) 
			{
                out.println("<tr>");
                out.println("<td>" + rs.getString("seatNo") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("class") + "</td>");
                out.println("<td>" + rs.getInt("totalMarks") + "</td>");
                out.println("<td>" + rs.getDouble("percentage") + "%</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            if (page > 1) 
			{
                out.println("<a href='list?page=" + (page - 1) + "'>&#9664; Previous</a>");
            }

            if (hasNextPage(conn, (page * RECORDS_PER_PAGE))) 
			{
                out.println("<a href='list?page=" + (page + 1) + "'>Next &#9654;</a>");
            }

            out.println("</div>");
            out.println("</div>");
            out.println("</body></html>");
        } 
		catch (SQLException | ClassNotFoundException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            try {
                if (rs != null) 
				{
                    rs.close();
                }
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

    private boolean hasNextPage(Connection conn, int nextPageOffset) throws SQLException 
	{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try 
		{
            String sql = "SELECT 1 FROM students LIMIT 1 OFFSET ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nextPageOffset);
            rs = stmt.executeQuery();
            return rs.next();
        } 
		finally 
		{
            if (rs != null) 
			{
                rs.close();
            }
            if (stmt != null) 
			{
                stmt.close();
            }
        }
    }
}

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet 
{

    private static final String URL = "jdbc:postgresql://localhost:5432/university";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        String name = request.getParameter("name");
        String rollNo = request.getParameter("rollNo");
        double fees = Double.parseDouble(request.getParameter("fees"));
        String status = request.getParameter("status");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        //out.write("<html><head><title>Registration successful</title>");


        try 
		{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            insertStudents(out,connection, name, rollNo, fees, status);
            connection.close();
        } 
		catch (SQLException | ClassNotFoundException e) 
		{
            String errorMessage = e.getMessage();
            out.write("<h1 style='text-align: center; color: red;'>Error occurred: " + errorMessage + "</h1>");
        }

        out.write("</body></html>");
		response.sendRedirect("registrationSuccess.jsp");
    }


    private void insertStudents(PrintWriter out,Connection connection, String name, String rollNo, Double fees, String status) throws SQLException 
	{
        String sql = "INSERT INTO students (name, roll_no, fees, status) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, rollNo);
        statement.setDouble(3, fees);
        statement.setString(4, status);
        statement.executeUpdate();

        statement.executeUpdate();
        statement.close();
		//out.write("<h1 style='text-align: center; color: green;'>Insert successful </h1>");

    }
}


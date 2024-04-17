import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class Save extends HttpServlet 
{

    private static final String URL = "jdbc:postgresql://localhost:5432/interview";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
        String firstName, lastName, phoneNumber, emailID, address;
        firstName = req.getParameter("first-name");
        lastName = req.getParameter("last-name");
        phoneNumber = req.getParameter("number");
        emailID = req.getParameter("email");
        address = req.getParameter("address");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try 
		{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            createTable(connection, out);
            insertData(connection, out, firstName, lastName, phoneNumber, emailID, address);
            connection.close();
        } 
		catch (SQLException | ClassNotFoundException e) 
		{
            String errorMessage = e.getMessage();
            out.write("<h1 style='text-align: center; color: red;'>Error occurred: " + errorMessage + "</h1>");
        }
    }

    private void createTable(Connection connection, PrintWriter out) throws SQLException 
	{
        String sql = "CREATE TABLE IF NOT EXISTS interview_data (" +
                "id SERIAL PRIMARY KEY, " +
                "first_name VARCHAR(100), " +
                "last_name VARCHAR(100), " +
                "phone_number VARCHAR(15), " +
                "email VARCHAR(200), " +
                "address TEXT" +
                ")";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    private void insertData(Connection connection, PrintWriter out, String firstName, String lastName, String phoneNumber, String email, String address) throws SQLException 
	{
        String sql = "INSERT INTO interview_data (first_name, last_name, phone_number, email, address) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, phoneNumber);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, address);

        preparedStatement.executeUpdate();
        preparedStatement.close();
        out.write("<h1 style='text-align: center; color: green;'>Data saved successfully</h1>");
    }
	doPost(req, res);
}

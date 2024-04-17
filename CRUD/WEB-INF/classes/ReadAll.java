import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class ReadAll extends HttpServlet 
{

    private static final String URL = "jdbc:postgresql://localhost:5432/employee_crud";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.write("<html><head><title>CRUD Operation</title>");
        out.write("<style>");
        out.write(".container {");
        out.write("  display: flex;");
        out.write("  justify-content: center;");
        out.write("  align-items: center;");
        out.write("  height: 80vh;");
        out.write("}");
        out.write(".box {");
        out.write("  background-color: #fff;");
        out.write("  border-radius: 10px;");
        out.write("  padding: 20px;");
        out.write("  box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);");
        out.write("}");
        out.write("button {");
        out.write("  background-color: #007bff; color: white; border: none; border-radius: 5px; padding: 10px 20px; margin: 5px; cursor: pointer; transition: background-color 0.3s ease;");
        out.write("}");
        out.write("table {");
        out.write("  width: 100%;");
        out.write("  border-collapse: collapse;");
        out.write("}");
        out.write("th, td {");
        out.write("  border: 1px solid #ddd;");
        out.write("  padding: 8px;");
        out.write("  text-align: left;");
        out.write("}");
        out.write("th {");
        out.write("  background-color: #f2f2f2;");
        out.write("}");
        out.write("</style>");
        out.write("</head><body>");
        out.write("<div class=\"container\">");
        out.write("<div class=\"box\">");
        try 
		{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            displayEmployees(connection, out);
            connection.close();
        } 
		catch (SQLException | ClassNotFoundException e) 
		{
            String errorMessage = e.getMessage();
            out.write("<h1 style='text-align: center; color: red;'>Error occurred: " + errorMessage + "</h1>");
        }
        out.write("<div style='text-align: center; margin-top: 20px;'>");
        out.write("<a href='./'><button>Go back</button></a>");
        out.write("</div>");
        out.write("</div>");
        out.write("</div>");
        out.write("</body></html>");
    }

    private static void displayEmployees(Connection connection, PrintWriter out) throws SQLException 
	{
        String sql = "SELECT * FROM employees";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        out.write("<table>");
        out.write("<tr>");
        out.write("<th>Emp_ID</th>");
        out.write("<th>Name</th>");
        out.write("<th>Email</th>");
        out.write("<th>Role</th>");
        out.write("<th>Phone Number</th>");
        out.write("</tr>");
        while (resultSet.next()) 
		{
            out.write("<tr>");
            out.write("<td>" + resultSet.getInt("emp_id") + "</td>");
            out.write("<td>" + resultSet.getString("emp_name") + "</td>");
            out.write("<td>" + resultSet.getString("emp_email") + "</td>");
            out.write("<td>" + resultSet.getString("emp_role") + "</td>");
            out.write("<td>" + resultSet.getString("emp_phone_number") + "</td>");
            out.write("</tr>");
        }
        out.write("</table>");
        resultSet.close();
        statement.close();
    }
}

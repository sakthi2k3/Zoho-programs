import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class FindForUpdate extends HttpServlet 
{

    private static final String URL = "jdbc:postgresql://localhost:5432/employee_crud";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
        int id;
        id = Integer.parseInt(req.getParameter("id"));
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.write("<html><head><title>CRUD Operation</title>");
        out.write("<style>");
        out.write(" .title { font-size: 24px; margin-bottom: 20px; color: #333; }");
        out.write(".container {");
        out.write("  display: flex;");
        out.write("  justify-content: center;");
        out.write("  align-items: center;");
        out.write("  height: 80vh;");
        out.write("}");
        out.write(".box {");
        out.write("  background-color: #fff;");
        out.write("  border-radius: 10px;");
        out.write("  padding: 30px;");
        out.write("  width: 300px;");
        out.write("  box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);");
        out.write("}");
        out.write("input[type='text'], input[type='email'] {");
        out.write("  width: 60%;");
        out.write("  padding: 8px;");
        out.write("  border-radius: 5px;");
        out.write("  border: 1px solid #ddd;");
        out.write("  margin-bottom: 10px;");
        out.write("}");
        out.write("input[type='submit'] {");
        out.write("  background-color: #007bff;");
        out.write("  color: white;");
        out.write("  border: none;");
        out.write("  border-radius: 5px;");
        out.write("  padding: 10px 20px;");
        out.write("  cursor: pointer;");
        out.write("  transition: background-color 0.3s ease;");
        out.write("  display: block;");
        out.write("  margin: auto; /* Center-align */");
        out.write("}");
        out.write("input[type='submit']:hover {");
        out.write("  background-color: #0056b3;");
        out.write("}");
        out.write("</style>");
        out.write("</head><body>");
        out.write("<div class=\"container\">");
        out.write("<div class=\"box\">");
        try 
		{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            displayEmployee(connection, id, out);
            connection.close();
        } 
		catch (SQLException | ClassNotFoundException e) 
		{
            String errorMessage = e.getMessage();
            out.write("<h1 style='text-align: center; color: red;'>Error occurred: " + errorMessage + "</h1>");
        }
        out.write("</div>");
        out.write("</div>");
        out.write("</body></html>");
    }

    private static void displayEmployee(Connection connection, int id, PrintWriter out) throws SQLException 
	{
        String sql = "SELECT * FROM employees where emp_id= ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {

            out.println("<div class=\"title\">Enter Employee Details</div>");
            out.println("<form action=\"update-two\" method=\"get\">");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"name\">Name:</label>");
            out.println("<input type=\"text\" id=\"name\" name=\"name\" value=\"" + resultSet.getString("emp_name") + "\">");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"id\">Employee ID:</label>");
            out.println("<input type=\"text\" id=\"id\" name=\"id\" value=\"" + resultSet.getString("emp_id") + "\">");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"phone\">Phone No:</label>");
            out.println("<input type=\"text\" id=\"phone\" name=\"phone\" value=\"" + resultSet.getString("emp_phone_number") + "\">");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"email\">Email:</label>");
            out.println("<input type=\"email\" id=\"email\" name=\"email\" value=\"" + resultSet.getString("emp_email") + "\">");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"role\">Employee Role:</label>");
            out.println("<input type=\"text\" id=\"role\" name=\"role\" value=\"" + resultSet.getString("emp_role") + "\">");
            out.println("</div>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</form>");
        } 
		else 
		{
            out.write("<h1 style='color:red'>No employee found with ID: " + id + "</h1>");
        }
        resultSet.close();
        preparedStatement.close();
    }
}

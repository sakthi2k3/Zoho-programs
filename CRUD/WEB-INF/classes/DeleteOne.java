import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class DeleteOne extends HttpServlet 
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
		out.write("<html><head><title>Delete Employee</title>");
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
		out.write("button{");
		out.write("background-color: #007bff; color: white; border: none; border-radius: 5px; padding: 10px 20px; margin: 5px; cursor: pointer; transition: background-color 0.3s ease;");
		out.write("}");
        out.write("</style>");
        out.write("</head><body>");
        out.write("<div class=\"container\">");
        out.write("<div class=\"box\">");
        try 
		{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            boolean success = deleteEmployee(connection, id);
            if (success) 
			{
                out.write("<h2 style='color:green'>Employee with ID " + id + " deleted successfully!</h2>");
            } 
			else 
			{
                out.write("<h2 style='color:red'>No employee found with ID: " + id + "</h2>");
            }
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

    private static boolean deleteEmployee(Connection connection, int id) throws SQLException 
	{
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        preparedStatement.close();
        return rowsAffected > 0;
    }
}

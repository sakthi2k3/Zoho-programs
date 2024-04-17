import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class AddOperation extends HttpServlet 
{

    private static final String URL = "jdbc:postgresql://localhost:5432/employee_crud";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
        String name, phone, email, role;
        int id;
        name = req.getParameter("name");
        id = Integer.parseInt(req.getParameter("id"));
        phone = req.getParameter("phone");
        email = req.getParameter("email");
        role = req.getParameter("role");
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();

        out.write("<html><head><title>CRUD Operation</title>");
        out.write("<style>");
        out.write(".container {");
        out.write("  display: flex;");
        out.write("  justify-content: center;");
        out.write("  align-items: center;");
        out.write("  height: 100vh;");
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
            createTable(connection,out);
            insertEmployee(out,connection, name, id, phone, email, role);
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

    private void createTable(Connection connection,PrintWriter out) throws SQLException 
	{
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                     "emp_id SERIAL PRIMARY KEY, " +
                     "emp_name VARCHAR(100), " +
                     "emp_email VARCHAR(200), " +
                     "emp_phone_number VARCHAR(15), " +
                     "emp_role VARCHAR(30)" +
                     ")";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        //out.write("<h1 style='text-align: center; color: green;'>Creation successful</h1>");
        statement.close();
    }

    private void insertEmployee(PrintWriter out,Connection connection, String name, int id, String phone, String email, String role) throws SQLException 
	{
        String sql = "INSERT INTO employees (emp_id, emp_name, emp_email, emp_phone_number, emp_role) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, phone);
        preparedStatement.setString(5, role);

        preparedStatement.executeUpdate();
        preparedStatement.close();
		out.write("<h1 style='text-align: center; color: green;'>Insert successful </h1>");

    }
}

		/*
		PrintWriter out=res.getWriter();
		out.write("<html><head><title>Calculated Salary</title></head><body>");
		out.write("<h1 style='text-align: center; color: red;'>Name: "+name+" </h1>");
		out.write("<h1 style='text-align: center; color: red;'>Id: "+id+" </h1>");
		out.write("<h1 style='text-align: center; color: red;'>Phone: "+phone+" </h1>");
		out.write("<h1 style='text-align: center; color: red;'>Email: "+email+" </h1>");
		out.write("<h1 style='text-align: center; color: red;'>Role "+role+" </h1>");

		out.write("</body></html>");
		*/
		

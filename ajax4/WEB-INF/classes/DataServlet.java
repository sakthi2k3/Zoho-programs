import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class DataServlet extends HttpServlet 
{

    private static final String URL = "jdbc:postgresql://localhost:5432/student_management2";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        int regNo = Integer.parseInt(request.getParameter("regNo"));
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String cgpa = request.getParameter("cgpa");

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) 
		{
			Class.forName("org.postgresql.Driver");
            String query = "INSERT INTO students (reg_no, name, dob, address, email, cgpa) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, regNo);
            statement.setString(2, name);
            statement.setString(3, dob);
            statement.setString(4, address);
            statement.setString(5, email);
            statement.setDouble(6, Double.parseDouble(cgpa));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) 
			{
                response.getWriter().write("Student inserted successfully");
            } 
			else 
			{
                response.getWriter().write("Failed to insert student");
            }
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>");
        htmlContent.append("<html lang=\"en\">");
        htmlContent.append("<head>");
        htmlContent.append("<meta charset=\"UTF-8\">");
        htmlContent.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        htmlContent.append("<title>Student List</title>");
        htmlContent.append("<style>");
        htmlContent.append("table {");
        htmlContent.append("width: 100%;");
        htmlContent.append("border-collapse: collapse;");
        htmlContent.append("}");
        htmlContent.append("th, td {");
        htmlContent.append("padding: 8px;");
        htmlContent.append("text-align: left;");
        htmlContent.append("border-bottom: 1px solid #ddd;");
        htmlContent.append("}");
        htmlContent.append("th {");
        htmlContent.append("    background-color: #f2f2f2;");
        htmlContent.append("}");
        htmlContent.append("</style>");
        htmlContent.append("</head>");
        htmlContent.append("<body>");
        htmlContent.append("<h1>Student List</h1>");
        htmlContent.append("<table>");
        htmlContent.append("<tr>");
        htmlContent.append("<th>Registration No</th>");
        htmlContent.append("<th>Name</th>");
        htmlContent.append("<th>Date of Birth</th>");
        htmlContent.append("<th>Address</th>");
        htmlContent.append("<th>Email</th>");
        htmlContent.append("<th>CGPA</th>");
        htmlContent.append("</tr>");

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) 
		{
			Class.forName("org.postgresql.Driver");
            String query = "SELECT * FROM students";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) 
			{
                htmlContent.append("<tr>");
                htmlContent.append("<td>").append(resultSet.getInt("reg_no")).append("</td>");
                htmlContent.append("<td>").append(resultSet.getString("name")).append("</td>");
                htmlContent.append("<td>").append(resultSet.getString("dob")).append("</td>");
                htmlContent.append("<td>").append(resultSet.getString("address")).append("</td>");
                htmlContent.append("<td>").append(resultSet.getString("email")).append("</td>");
                htmlContent.append("<td>").append(resultSet.getDouble("cgpa")).append("</td>");
                htmlContent.append("</tr>");
            }
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
            htmlContent.append("<tr><td colspan=\"6\">Failed to retrieve students</td></tr>");
        }

        htmlContent.append("</table>");
        htmlContent.append("</body>");
        htmlContent.append("</html>");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(htmlContent.toString());
    }
}

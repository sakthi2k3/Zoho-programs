import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class Login extends HttpServlet {
    private static final String URL = "jdbc:postgresql://localhost:5432/login_credentials";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try 
		{
            Class.forName("org.postgresql.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password);

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) 
					{
                        response.getWriter().write("<h2 style='color:green'>Welcome " + resultSet.getString("name") + "</h2>");
                    } 
					else 
					{
                        response.getWriter().write("failure");
                    }
                }
            }
        } 
		catch (SQLException | ClassNotFoundException e) 
		{
            response.getWriter().write(e.getMessage());
        }
    }
}

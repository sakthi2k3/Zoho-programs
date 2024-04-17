import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class EmployeeSalary extends HttpServlet 
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		int days=Integer.parseInt(req.getParameter("days"));
		double salary=999*days;
		PrintWriter out=res.getWriter();
		out.write("<html><head><title>Calculated Salary</title></head><body>");
		out.write("<h1 style='text-align: center; color: red;'>The calculated salary is: "+salary+" </h1>");
		out.write("</body></html>");
	}	
}

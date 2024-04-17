import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
public class PrintPrograming extends HttpServlet 
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<h1>Programing</h1>");
    }
}

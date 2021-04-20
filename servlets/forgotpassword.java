import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class forgotpassword extends HttpServlet
{  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();    
		try
		{  
			Class.forName("com.mysql.cj.jdbc.Driver");   
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"lms","root","amith");   
			
			String userid = request.getParameter("userid");
            String password = request.getParameter("password");

			PreparedStatement ps=con.prepareStatement("update member set password=? where userid =?");
			
			ps.setString(1, password); 
			ps.setString(2, userid); 
			int i=ps.executeUpdate();
			if(i!=0)
			{
				out.println("Password changed successfully!");
				out.println("<br><a HREF=\"homepage.html\">Click here to go back to the homepage</a>"); 
			}
			else if(i==0)
			{
				out.print("No rows found");
			} 
		}

		catch (Exception ex)
		{
			out.println("error"); 
		}  
		out.close();
	}  
}
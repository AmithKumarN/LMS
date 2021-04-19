import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class updatebook extends HttpServlet
{  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();    
		try
		{  
			Class.forName("com.mysql.cj.jdbc.Driver");   
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"lms","root","amith");   
			
			Integer Qty = Integer.parseInt(request.getParameter("Qty"));
			String ISBN = request.getParameter("ISBN");

			PreparedStatement ps=con.prepareStatement("update book set qty=? where isbn =?");
			
			ps.setInt(1, Qty); 
			ps.setString(2, ISBN); 
			int i=ps.executeUpdate();
			if(i!=0)
			{
				out.println("Updating rows");
				out.println("<br><a HREF=\"viewbook.html\">view records after updating..</a>"); 
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
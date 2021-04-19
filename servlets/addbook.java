import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class addbook extends HttpServlet
{  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String ISBN = request.getParameter("ISBN");
		String Title = request.getParameter("Title");  
		String Author = request.getParameter("Author");  
		String Genre = request.getParameter("Genre");  
		String Publisher = request.getParameter("Publisher");
		String Qty = request.getParameter("Qty");
		
		try
		{  
			Class.forName("com.mysql.cj.jdbc.Driver");   
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"lms","root","amith");   
			PreparedStatement ps=con.prepareStatement("insert into book values(?,?,?,?,?,?);");  

			ps.setString(1,ISBN);  
			ps.setString(2,Title);  
			ps.setString(3,Author);  
			ps.setString(4,Genre);  
			ps.setString(5,Publisher);
			ps.setString(6,Qty);  
			
			int i = ps.executeUpdate();  
			if(i>0)  
				out.print("Succesfully book is added"); 
			out.println("<br><a HREF=\"viewbook.html\">view book records..</a>"); 

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}  
		out.close();  
	}  
}
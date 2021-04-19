import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class checkfine extends HttpServlet
{  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		out.println("<html><body>");  
		try
		{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"lms","root","amith");   
			Statement stmt = con.createStatement();  
            String userid = request.getParameter("userid");
			ResultSet rs = stmt.executeQuery("select * from transaction where userID=\""+userid+"\";");  
			out.println("<table border=1 width=50% height=50%>");  
			out.println("<tr><th>User ID</th><th>ISBN</th><th>Borrow Date</th><th>Due Date</th><th>Return Date</th><th>Fine</th></tr>");  
			while (rs.next()) 
			{  
				String c1 = rs.getString(1);  
				String c2 = rs.getString(2);  
				String c3 = rs.getString(3);
				String c4 = rs.getString(4);
				String c5 = rs.getString(5);
				String c6 = rs.getString(6);
				out.println("<tr><td>" + c1 + "</td><td>" + c2 + "</td><td>" + c3 + "</td><td>" + c4 + "</td><td>" + c5 +  "</td><td>" + c6 + "</td></tr>");   
			}  
			out.println("</table>");  
			out.println("</html></body>");  
			con.close();  
		}  
		catch (Exception ex)
		{
			out.println("error"); 
            out.println(ex.toString());
		}
		out.close();
	}  
}
import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class searchtitle extends HttpServlet
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
            String tit = request.getParameter("Title");
			ResultSet rs = stmt.executeQuery("select * from book where title=\""+tit+"\";");  
			out.println("<table border=1 width=50% height=50%>");  
			out.println("<tr><th>ISBN</th><th>Title</th><th>Author</th><th>Genre</th><th>Publisher</th><th>Qty</th></tr>");  
			while (rs.next()) 
			{  
				String ISBN1 = rs.getString("ISBN");  
				String Title = rs.getString("Title");  
				String Author = rs.getString("Author");
				String Genre = rs.getString("Genre");
				String Publisher = rs.getString("Publisher");
				String Qty = rs.getString("Qty");
				out.println("<tr><td>" + ISBN1 + "</td><td>" + Title + "</td><td>" + Author + "</td><td>" + Genre + "</td><td>" + Publisher +  "</td><td>" + Qty + "</td></tr>");   
			}  
			out.println("</table>");  
			out.println("</html></body>");  
			con.close();  
		}  
		catch (Exception ex)
		{
			out.println("error"); 
		}
		out.close();
	}  
}
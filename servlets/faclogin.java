import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class faclogin extends HttpServlet
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
            String uid = request.getParameter("userid");
            String pass = request.getParameter("password");
			ResultSet rs = stmt.executeQuery("select * from member where userID=\""+uid+"\";");
            if(rs.next())
            {
                String ogpass = rs.getString("password");
                if(pass.equals(ogpass))
                {
                    out.println("<script>location.href='bookuser.html'</script>");
                }
                else
                    out.println("Invalid user id/password");
            }
            else
                out.println("Invalid user id/password");
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
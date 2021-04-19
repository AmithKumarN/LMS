import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class returnbook extends HttpServlet
{  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
        String userid = request.getParameter("userid");
		String isbn = request.getParameter("isbn");
		
		try
		{  
			Class.forName("com.mysql.cj.jdbc.Driver");   
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"lms","root","amith");
			PreparedStatement ps;
            ResultSet rs;
            ps=con.prepareStatement("select qty from book where isbn=?");
            ps.setString(1, isbn);
            rs=ps.executeQuery();
            if(rs.next())
            {
                Integer qty = Integer.parseInt(rs.getString("qty"));
                qty += 1;
                ps=con.prepareStatement("update book set qty=? where isbn=?");
                ps.setInt(1, qty);
                ps.setString(2, isbn);
                ps.executeUpdate();
                ps=con.prepareStatement("delete from transaction where userid=? and isbn=?");
                ps.setString(1,userid);
                ps.setString(2,isbn);
                
                int i = ps.executeUpdate();  
                if(i>0)  
                    out.print("Book returned!!"); 
                out.println("<br><a HREF=\"bookuser.html\">Click here to go back to dashboard.</a>"); 
            }
            else
            {
                out.println("Invalid ISBN number");
                System.exit(0);
            }
            
		}
		catch (Exception ex)
		{
            out.println("Some error has occurred!");
            out.println(ex.toString());
		}  
		out.close();  
	}  
}
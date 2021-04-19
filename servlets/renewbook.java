import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class renewbook extends HttpServlet
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
            ps=con.prepareStatement("select usertype from member where userID=?");
            ps.setString(1,userid);
            rs=ps.executeQuery();
            if(rs.next())
            {
                String type = rs.getString("usertype");
                ps=con.prepareStatement("select datediff(curdate(), duedate) as days, fine from transaction where userid=? and isbn=?");
                ps.setString(1,userid);
                ps.setString(2,isbn);
                rs=ps.executeQuery();
                if(rs.next())
                {
                    Integer days = rs.getInt("days");
                    Integer fine = rs.getInt("fine");
                    Date newduedate = new Date(new java.util.Date().getTime());
                    if(type.equals("Student"))
                    {
                        newduedate = new Date(newduedate.getTime()+ 7*24*60*60*1000);
                        fine += days*20;
                    }
                    else
                    {
                        newduedate = new Date(newduedate.getTime()+ 14*24*60*60*1000);
                        fine += days*10;
                    }
                
                    ps=con.prepareStatement("update transaction set duedate=?, fine=? where userid=? and isbn=?");
                    ps.setDate(1,newduedate);
                    ps.setInt(2,fine);
                    ps.setString(3,userid);
                    ps.setString(4,isbn);


                    int i = ps.executeUpdate();  
                    if(i>0)  
                        out.print("Book renewed. Happy Reading!!"); 
                    out.println("<br><a HREF=\"bookuser.html\">Click here to go back to dashboard.</a>"); 
                }
                else
                {
                    out.println("Invalid transaction details.");
                    System.exit(0);
                }
            }
            else
            {
                out.println("Invalid user id");
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
import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class borrowbook extends HttpServlet
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

                ps=con.prepareStatement("select qty from book where isbn=?");
                ps.setString(1, isbn);
                rs=ps.executeQuery();
                if(rs.next())
                {
                    Integer qty = Integer.parseInt(rs.getString("qty"));
                    if(qty <= 0)
                    {
                        out.println("Book out of stock");
                        System.exit(0);
                    }
                    else
                    {
                        qty -= 1;
                        ps=con.prepareStatement("update book set qty=? where isbn=?");
                        ps.setInt(1, qty);
                        ps.setString(2, isbn);
                        ps.executeUpdate();
                        ps=con.prepareStatement("insert into transaction values(?,?,?,?,?,?);");

                        Date borrowdate = new Date(new java.util.Date().getTime());
                        Date duedate;
                        if(type.equals("Student"))
                            duedate = new Date(borrowdate.getTime()+ 7*24*60*60*1000);
                        else
                            duedate = new Date(borrowdate.getTime()+ 14*24*60*60*1000);
                        ps.setString(1,userid);
                        ps.setString(2,isbn);
                        ps.setDate(3,borrowdate);
                        ps.setDate(4,duedate);
                        ps.setNull(5,Types.NULL);
                        ps.setInt(6,0);
                        
                        int i = ps.executeUpdate();  
                        if(i>0)  
                            out.print("Happy Reading!!"); 
                        out.println("<br><a HREF=\"bookuser.html\">Click here to go back to dashboard.</a>"); 
                    }
                }
                else
                {
                    out.println("Invalid ISBN number");
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
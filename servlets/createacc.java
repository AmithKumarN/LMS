import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class createacc extends HttpServlet
{  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");  
        String name = request.getParameter("name");  
        Integer age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String emailid = request.getParameter("emailid");  
        String dept = request.getParameter("dept");
        Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
        String type = request.getParameter("type");
        
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");   
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"lms","root","amith");   
            PreparedStatement ps=con.prepareStatement("insert into member values(?,?,?,?,?,?,?,?,?);");  

            ps.setString(1,userid);  
            ps.setString(2,password);  
            ps.setString(3,name);  
            ps.setInt(4,age);  
            ps.setString(5,gender);
            ps.setString(6,emailid);  
            ps.setString(7,dept);  
            ps.setDate(8,dob);  
            ps.setString(9,type);  
            
            int i = ps.executeUpdate();  
            if(i>0)  
                out.println("Account created successfully!"); 
            if(type.equals("Faculty"))
                out.println("<br><a HREF=\"faclogin.html\">Click here to go login page</a>"); 
            else if(type.equals("Student"))
                out.println("<br><a HREF=\"stulogin.html\">Click here to go login page</a>"); 

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }  
        out.close();  
    }  
}
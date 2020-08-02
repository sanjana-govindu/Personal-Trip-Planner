import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LSuccessServ
 */
@WebServlet("/verify")
public class LSuccessServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static int userid;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LSuccessServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: Hello World  ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String)request.getParameter("email");
		String password = (String)request.getParameter("pwd");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fullstackproject?useSSL=false","root","root");
			Statement s= con.createStatement();
			ResultSet rs=s.executeQuery("select * from users where email='"+username+"' and password='"+password+"'");
			if(rs.next())
			{
				Regserv.id=1;
				userid=rs.getInt(1);
				request.setAttribute("user", userid);
				RequestDispatcher rd2=request.getRequestDispatcher("plans");
				rd2.forward(request, response);
			}
			else
			{
				RequestDispatcher rd1= request.getRequestDispatcher("errorpage.html");
				rd1.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}

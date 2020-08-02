import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Regserv
 */
@WebServlet("/register")
public class Regserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static int id=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regserv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fn= request.getParameter("fn");
		String ln= request.getParameter("ln");
		String email= request.getParameter("email");
		int phno= (int)Long.parseLong(request.getParameter("phno"));
		String pass= request.getParameter("pass");
		String gen= request.getParameter("gender");
		String dob= request.getParameter("dob");
		java.sql.Date udob = null;
		try {
			java.util.Date bdate = new SimpleDateFormat("yyyy-mm-dd").parse(dob);
			udob= new java.sql.Date(bdate.getTime());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cntry=request.getParameter("country");
		String city=request.getParameter("city");
		int pin=Integer.parseInt(request.getParameter("pin"));
		int num=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fullstackproject?useSSL=false","root","root");
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*)'num' from users;");
			if(rs.next())
			{
				num=rs.getInt("num")+1;
				id=1;
				LSuccessServ.userid=num;
			}
			PreparedStatement ps=con.prepareStatement("insert into users values(?,?,?,?,?,?,?,?,?,?,?);");
			ps.setInt(1,num);
			ps.setString(2, fn);
			ps.setString(3, ln);
			ps.setString(4, email);
			ps.setInt(5, phno);
			ps.setString(6, pass);
			ps.setString(7, gen);
			ps.setDate(8, udob);
			ps.setString(9, cntry);
			ps.setString(10, city);
			ps.setInt(11, pin);
			ps.executeUpdate();
			RequestDispatcher rd= request.getRequestDispatcher("myplans.jsp");
			rd.forward(request, response);
			con.close();
		} catch (Exception e) {
			RequestDispatcher rd= request.getRequestDispatcher("errorpage.html");
			rd.forward(request, response);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/hsubmit")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
		if(Regserv.id!=1)
		{
			Cookie c1= new Cookie("src",request.getParameter("src"));
			Cookie c2= new Cookie("dst",request.getParameter("dest"));
			Cookie c3= new Cookie("sd",request.getParameter("sd"));
			Cookie c4= new Cookie("ed",request.getParameter("ed"));
			Cookie c5= new Cookie("np",request.getParameter("np"));
			response.addCookie(c1);
			response.addCookie(c2);
			response.addCookie(c3);
			response.addCookie(c4);
			response.addCookie(c5);
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		else
		{
			String src=request.getParameter("src");
			String dst=request.getParameter("dest");
			String sd=request.getParameter("sd");
			String ed=request.getParameter("ed");
			String np=request.getParameter("np");
			java.util.Date sd0,sd2;
			java.sql.Date sd1=null,sd3=null;
			try {
			sd0= new SimpleDateFormat("yyyy-mm-dd").parse(sd);
			sd1= new java.sql.Date(sd0.getTime());
			sd2= new SimpleDateFormat("yyyy-mm-dd").parse(ed);
			sd3= new java.sql.Date(sd2.getTime());
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fullstackproject?useSSL=false","root","root");
				PreparedStatement ps=con.prepareStatement("insert into plans values(?,?,?,?,?,?)");
				ps.setInt(1, LSuccessServ.userid);
				ps.setString(2, src);
				ps.setString(3,dst);
				ps.setDate(4, sd1);
				ps.setDate(5, sd3);
				ps.setInt(6, Integer.parseInt(np));
				ps.executeUpdate();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			request.getRequestDispatcher("myplans.jsp").forward(request, response);
		}
	}

}
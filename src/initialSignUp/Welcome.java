package initialSignUp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database_helper.DBHelper;


/**
 * Servlet implementation class Welcome
 */
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
	private DBHelper db  = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
    	//initialize app and get tweets
    	db = new DBHelper();
    	conn = db.dbConnect();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("login called");
		if(request.getParameter("submit").equals("login"))
		{
			System.out.println("login called");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			if(userName == null || password == null)
			{
				response.sendRedirect("homePage.jsp?status=emptyFields");
			}
			System.out.println(userName);
			String name = db.authenticateUser(conn,userName,password);
			if(!name.equals(""))
			{
				System.out.println("user validated. name is "+ name);
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("userName",userName);
				RequestDispatcher rd = request.getRequestDispatcher("userProfile.jsp");
				rd.forward(request, response);
			}
			else
			{
				response.sendRedirect("homePage.jsp?status=invalidInput");
			}
		}
		else if(request.getParameter("submit").equals("signup")){
			System.out.println("signup called");
			RequestDispatcher rd = request.getRequestDispatcher("newSignUp.jsp");
			rd.forward(request, response);
		}
		
	}

}

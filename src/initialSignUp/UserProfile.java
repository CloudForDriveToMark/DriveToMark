package initialSignUp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserProfile
 */
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
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
		if(request.getParameter("submit").equals("advertise")){
			System.out.println("advertise called");
			RequestDispatcher rd = request.getRequestDispatcher("advertise.jsp");
			rd.forward(request, response);
		}else if(request.getParameter("submit").equals("lookUp")){
			System.out.println("signup called");
			RequestDispatcher rd = request.getRequestDispatcher("lookUp.jsp");
			rd.forward(request, response);
		}else if(request.getParameter("submit").equals("viewStatus")){
			System.out.println("signup called");
			RequestDispatcher rd = request.getRequestDispatcher("lookUp.jsp");
			rd.forward(request, response);
	}
	}

}

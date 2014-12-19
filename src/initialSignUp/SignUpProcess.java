package initialSignUp;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import models.Employee;
import database_helper.DBHelper;

/**
 * Servlet implementation class SignUpProcess
 */
public class SignUpProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpProcess() {
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
		System.out.println("coming here");
		RequestDispatcher rd = null;
		DBHelper db = new DBHelper();
		Connection conn = db.dbConnect();
		String userName = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String stAdd = request.getParameter("stAdd");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String zipCode = request.getParameter("zip");
		
		//set employee object for signup completion
		Employee employee = new Employee(userName,password,name,contact,stAdd,city,state,country,zipCode);
		System.out.println(userName+password+name+contact+stAdd+city+state+country+zipCode);
		if(db.createAccount(conn, employee)>0){
			System.out.println("signUp successfull");
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("userName",userName);
			rd = request.getRequestDispatcher("userProfile.jsp");
			rd.include(request, response);
		}else{
			rd = request.getRequestDispatcher("errorSignUp.jsp");
			rd.include(request, response);
		}
		
	}

}

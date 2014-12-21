package advertiseAndLookup;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Advertisement;
import models.Employee;
import database_helper.DBHelper;

/**
 * Servlet implementation class LookUp
 */
public class LookUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookUp() {
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
		System.out.println("coming to lookup");
		RequestDispatcher rd = null;
		DBHelper db = new DBHelper();
		Connection conn = db.dbConnect();
		int people = 0;
		int distance = 0;
		String startAddress = null;
		String startZip = null;
		String destinationAddress = null;
		
		//load default map from user's city if it is from direct user profile
		if (request.getParameter("submit").equals("filter")) {
			// check which filters are applied
			if(request.getParameter("startAddress")!=null){
				startAddress = request.getParameter("startAddress");
			}
			if(request.getParameter("startZip")!=null){
				startZip = request.getParameter("startZip");
			}
			if(request.getParameter("destinationAddress")!=null){
				destinationAddress = request.getParameter("destinationAddress");
			}
			people = Integer.parseInt(request.getParameter("people"));
			distance = Integer.parseInt(request.getParameter("distance"));
			System.out.println("distance selected is " + distance);
			System.out.println("caling lookup filter");
			ArrayList<Advertisement> advertisementResult = db.lookUpWithFilter(
					conn, request.getSession().getAttribute("userName")
							.toString(), people, distance,startAddress,startZip,destinationAddress);

			// set employee object for signup completion
			
			if(advertisementResult!=null){
				System.out.println("here are lookup results successfull");
				int size = advertisementResult.size();
				Advertisement[] advertiseArray =  new Advertisement[size];
				advertiseArray = advertisementResult.toArray(advertiseArray);
				Employee[] employeeArray = new Employee[size];
				ArrayList<Employee> employeeResult =  db.populateEmployeeArray(conn,advertiseArray);
				employeeArray = employeeResult.toArray(employeeArray);
				request.getSession().setAttribute("advertisementArray", advertiseArray);
				request.getSession().setAttribute("employeeArray", employeeArray);
				rd = request.getRequestDispatcher("lookUpResult.jsp");
				rd.forward(request, response);
			}else{
				rd = request.getRequestDispatcher("errorSignUp.jsp");
				rd.forward(request, response);
			}
		}
		
		
	}
	}



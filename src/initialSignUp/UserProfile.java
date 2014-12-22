package initialSignUp;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database_helper.DBHelper;
import models.Advertisement;
import models.Employee;
import models.Request;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		DBHelper db = new DBHelper();
		Connection conn = db.dbConnect();

		// set user status
		Request[] requestReceiveArray, requestSentArray = null;
		ArrayList<Request> requestReceived = db.populateRequestReceivedByUser(
				conn, request.getSession().getAttribute("userName").toString());
		ArrayList<Request> requestSent = db.populateRequestSentByUser(conn,
				request.getSession().getAttribute("userName").toString());
		if (requestReceived != null) {
			requestReceiveArray = new Request[requestReceived.size()];
			requestReceiveArray = requestReceived.toArray(requestReceiveArray);
			request.getSession().setAttribute("requestReceiveArray",
					requestReceiveArray);
		}
		if (requestSent != null) {
			requestSentArray = new Request[requestSent.size()];
			requestSentArray = requestSent.toArray(requestSentArray);
			request.getSession().setAttribute("requestSentArray",
					requestSentArray);
		}

		if (request.getParameter("submit").equals("advertise")) {
			System.out.println("advertise called");
			rd = request.getRequestDispatcher("advertise.jsp");
			rd.forward(request, response);
		} else if (request.getParameter("submit").equals("lookUp")) {

			ArrayList<Advertisement> advertisementResult = db.lookUpDefault(
					conn, request.getSession().getAttribute("userName")
							.toString());

			// set employee object for signup completion
			if (advertisementResult != null) {
				System.out.println("here are lookup results successfull");
				int size = advertisementResult.size();
				Advertisement[] advertiseArray = new Advertisement[size];
				Employee[] employeeArray = new Employee[size];
				advertiseArray = advertisementResult.toArray(advertiseArray);
				ArrayList<Employee> employeeResult = db.populateEmployeeArray(conn, advertiseArray);
				employeeArray = employeeResult.toArray(employeeArray);
				request.getSession().setAttribute("advertisementArray",advertiseArray);
				request.getSession().setAttribute("employeeArray",
						employeeArray);
				rd = request.getRequestDispatcher("lookUpResult.jsp");
				rd.forward(request, response);
			} else {
				rd = request.getRequestDispatcher("errorSignUp.jsp");
				rd.forward(request, response);
			}

		} else if (request.getParameter("submit").equals("viewStatus")) {
			System.out.println("viewStatus called");
			rd = request.getRequestDispatcher("viewStatus.jsp");
			rd.forward(request, response);
		}
	}

}

package advertiseAndLookup;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Advertisement;
import models.Employee;
import database_helper.DBHelper;

/**
 * Servlet implementation class Advertise
 */
public class Advertise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Advertise() {
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
		DBHelper db = new DBHelper();
		Connection conn = db.dbConnect();
		RequestDispatcher rd = null;
		
		String userName = request.getSession().getAttribute("userName").toString();
		String carModel = request.getParameter("carModel");
		String capacity = request.getParameter("capacity");
		String required = request.getParameter("required");
		String charge = request.getParameter("charge");
		String startStAdd = request.getParameter("startStAdd");
		String startCity = request.getParameter("startCity");
		String startZip = request.getParameter("startZip");
		String officeAdd = request.getParameter("officeAdd");
		String officeZip = request.getParameter("officeZip");
		String message = request.getParameter("message");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		
		
		//set employee object for signup completion
		Advertisement advertisement = new Advertisement(userName,carModel,capacity,required,charge,startStAdd,startCity,startZip,officeAdd,
				officeZip,message,date,time);
		
		if(db.createAdvertisement(conn, advertisement)>0){
			System.out.println("advertisement successfull");
			rd = request.getRequestDispatcher("userProfile.jsp?adv=y");
			rd.forward(request, response);
		}else{
			rd = request.getRequestDispatcher("errorAdvertise.jsp");
			rd.forward(request, response);
		}
	}

}

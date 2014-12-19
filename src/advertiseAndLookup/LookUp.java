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
		String startCity = request.getParameter("city");
		String startZip = request.getParameter("zipStart");
		String people = request.getParameter("people");
		String officeAdd = request.getParameter("officeAdd");
		String officeZip = request.getParameter("officeZip");
		String distance = request.getParameter("distance");
		System.out.println("distance selected is "+ distance);
		ArrayList<Advertisement> advertisementResult = db.lookUp(conn, startCity,startZip,people,officeAdd,officeAdd,officeZip);
		
		//set employee object for signup completion
		
		if(advertisementResult!=null){
			System.out.println("here are lookup results successfull");
			int size = advertisementResult.size();
			Advertisement[] advertiseArray =  new Advertisement[size];
			advertiseArray = advertisementResult.toArray(advertiseArray);
			request.getSession().setAttribute("advertisementArray", advertiseArray);
			rd = request.getRequestDispatcher("lookUpResult.jsp");
			rd.include(request, response);
		}else{
			rd = request.getRequestDispatcher("errorSignUp.jsp");
			rd.include(request, response);
		}
		
	}
	}



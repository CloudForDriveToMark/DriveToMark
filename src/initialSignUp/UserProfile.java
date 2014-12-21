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
		RequestDispatcher rd = null;
		DBHelper db = new DBHelper();
		Connection conn = db.dbConnect();
		if(request.getParameter("submit").equals("advertise")){
			System.out.println("advertise called");
			rd = request.getRequestDispatcher("advertise.jsp");
			rd.forward(request, response);
		}else if(request.getParameter("submit").equals("lookUp")){
			
			ArrayList<Advertisement> advertisementResult = db.lookUpDefault(conn,request.getSession().getAttribute("userName").toString());
			
			//set employee object for signup completion
			
			if(advertisementResult!=null){
				System.out.println("here are lookup results successfull");
				int size = advertisementResult.size();
				Advertisement[] advertiseArray =  new Advertisement[size];
				advertiseArray = advertisementResult.toArray(advertiseArray);
				request.getSession().setAttribute("advertisementArray", advertiseArray);
				rd = request.getRequestDispatcher("lookUpResult.jsp");
				rd.forward(request, response);
			}else{
				rd = request.getRequestDispatcher("errorSignUp.jsp");
				rd.forward(request, response);
			}
			
		}else if(request.getParameter("submit").equals("viewStatus")){
			System.out.println("signup called");
			 rd = request.getRequestDispatcher("lookUp.jsp");
			rd.forward(request, response);
	}
	}

}

package advertiseAndLookup;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Request;
import database_helper.DBHelper;

/**
 * Servlet implementation class ViewStatus
 */
public class ViewStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewStatus() {
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
	 * @see HttpServlet#doPost(HttpServletRequest req uest, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		DBHelper db = new DBHelper();
		Connection conn = db.dbConnect();
		Request []requestReceived = (models.Request[])request.getSession().getAttribute("requestReceiveArray");
		if (request.getParameter("submit").equals("lookUp")) {
			rd = request.getRequestDispatcher("profileIndexAfterLogin.jsp");
			rd.forward(request, response);
		}else if(request.getParameter("submit").contains("approve")){
			System.out.println(request.getParameter("submit"));
			int RequestIndex = Integer.parseInt(request.getParameter("submit").substring(7));
			String requester = requestReceived[RequestIndex].getRequester();
			System.out.println(RequestIndex+" "+requester);
			db.updateRequestInfo(conn,request.getSession().getAttribute("userName")
					.toString(),requester);
			db.updateRequiredInAdvertisement(conn,requestReceived[RequestIndex].getAdvertisementId(),requestReceived[RequestIndex].getNumberOfPeople());
			
			//update requests in session as well
			requestReceived[RequestIndex].setApproved("yes");
			request.getSession().setAttribute("requestReceiveArray",requestReceived);
			
			rd = request.getRequestDispatcher("viewStatus.jsp");
			rd.forward(request, response);
		}
	}

}

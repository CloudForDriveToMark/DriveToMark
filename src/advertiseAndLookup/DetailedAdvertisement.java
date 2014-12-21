package advertiseAndLookup;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;

import database_helper.DBHelper;

/**
 * Servlet implementation class DetailedAdvertisement
 */
public class DetailedAdvertisement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailedAdvertisement() {
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
		int successfulRequestInsertInDb = 0;
		DBHelper db = new DBHelper();
		Connection conn = db.dbConnect();
		String requester = request.getSession().getAttribute("userName").toString(); 
		String  employeeToContactEmail = request.getSession().getAttribute("employeeToContactEmail").toString();
		String  employeeToContactName = request.getSession().getAttribute("employeeToContactName").toString();
		String messageToPublish = "Hi "+employeeToContactName+ " You have recieved a car request from "+requester;
		System.out.println("we are going to email "+ employeeToContactEmail + " by Amazon SNS");
		subscribeClientandPublish(employeeToContactEmail,messageToPublish);
		
		//save request details in database
		successfulRequestInsertInDb = db.insertRequest(conn, requester, employeeToContactEmail, System.currentTimeMillis() );
		if(successfulRequestInsertInDb ==0 ){
			System.out.println("User has already sent request to advertiser");
		}else{
			System.out.println("Request inserted successfully");
		}
		
	}

	private AmazonSNSClient subscribeClientandPublish(String employeeEmail, String messageToPublish) {
		// TODO Auto-generated method stub
		AmazonSNSClient snsClient = null;
		System.out.println("#################going to subscribe client###############");
		try {
			snsClient = new AmazonSNSClient(new PropertiesCredentials(DetailedAdvertisement.class
					.getResourceAsStream("AwsCredentials.properties")));
			System.out.println("got credentials");
		}catch (Exception e) {
			System.out.println(e);
		}
		
		snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));
		CreateTopicRequest createTopicRequest = new CreateTopicRequest("CarPoolRequest");
		CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
		String topic = createTopicResult.toString().substring(11);
		topic = topic.substring(0, topic.length() - 1);
		
		// subscribe to an SNS topic
		SubscribeRequest subRequest = new SubscribeRequest(topic, "email",employeeEmail);
		snsClient.subscribe(subRequest);
		
		// get request id for SubscribeRequest from SNS meta data
		System.out.println("SubscribeRequest - "+ snsClient.getCachedResponseMetadata(subRequest));
		System.out.println("Check your email and confirm subscription.");
		
		PublishRequest publishRequest = new PublishRequest(topic,messageToPublish);

		PublishResult publishResult = snsClient.publish(publishRequest);
		return snsClient;

	}

}

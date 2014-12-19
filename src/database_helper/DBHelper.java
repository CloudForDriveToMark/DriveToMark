package database_helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.user.AddressConverter;
import com.user.GoogleResponse;
import com.user.Result;

import models.Advertisement;
import models.Employee;

public class DBHelper {

	public Connection dbConnect()
	{
		Connection conn = null;
		String url =  "jdbc:mysql://twit.cqlk5i2ycxba.us-east-1.rds.amazonaws.com:3306/driveToMark";
		//String dbName = "twitter_sentiment";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "us341"; 
		String password = "sandy2203"; 
		
		
		try { 
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url,userName,password);
			System.out.println(conn);
			//conn.close(); 
			}
		catch (Exception e) { 
			e.printStackTrace(); } 
		return conn;
		
	}

	
	public String authenticateUser(Connection conn, String userName, String password) {
		// TODO Auto-generated method stub
		String name = "";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs=st.executeQuery("select * from employee where username='"+userName+"'and password='"+password+"'");
			if(rs.next())
			{
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
		
	}


	public int createAccount(Connection conn, Employee employee) {
		// TODO Auto-generated method stub
		int t = 0;
		try{
		PreparedStatement ps=conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?)");
		ps.setString(1,employee.getUserName());
		ps.setString(2,employee.getPassword());
		ps.setString(3,employee.getName());
		ps.setString(4,employee.getStAdd());
		ps.setString(5,employee.getCity());
		ps.setString(6,employee.getState());
		ps.setString(7,employee.getCountry());
		ps.setString(8,employee.getZipCode());
		ps.setString(9,employee.getContact());
		t=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
		}
		return t;
		
	}


	public int createAdvertisement(Connection conn, Advertisement advertisement) {
		// TODO Auto-generated method stub
		int t = 0;
		
		//calculate lat and long
		double latitude = 0;
		double longitude = 0;
		AddressConverter converter = new AddressConverter();
		try {
			GoogleResponse res = converter.convertToLatLong(advertisement.getStartStAdd()+" "+
					advertisement.getStartCity()+" "+advertisement.getStartZip());
			if (res.getStatus().equals("OK")) {
				for (Result result : res.getResults()) {

					latitude = Double.parseDouble(result.getGeometry().getLocation()
							.getLat());
					longitude = Double.parseDouble(result.getGeometry().getLocation()
							.getLng());
					System.out.println("Lattitude of address is :"
							+ result.getGeometry().getLocation().getLat());
					System.out.println("Longitude of address is :"
							+ result.getGeometry().getLocation().getLng());
					System.out.println("Location is "
							+ result.getGeometry().getLocation_type());
				}
			} else {
				System.out.println(res.getStatus());
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try{
		PreparedStatement ps=conn.prepareStatement("insert into advertise_car values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1,null);
		ps.setString(2,advertisement.getUserName());
		ps.setString(3,advertisement.getCarModel());
		ps.setInt(4,advertisement.getCapacity());
		ps.setInt(5,advertisement.getRequired());
		ps.setInt(6,advertisement.getCharge());
		ps.setString(7,advertisement.getStartStAdd());
		ps.setString(8,advertisement.getStartCity());
		ps.setString(9,advertisement.getStartZip());
		ps.setString(10,advertisement.getDestination());
		ps.setString(11,advertisement.getDestinationZip());
		ps.setString(12,advertisement.getMessage());
		ps.setString(13,advertisement.getDate());
		ps.setString(14,advertisement.getTime());
		ps.setDouble(15,latitude);
		ps.setDouble(16,longitude);
		
		t=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
		}
		return t;
	}


	public ArrayList<Advertisement> lookUp(Connection conn, String startCity, String startZip,
			String people, String officeAdd, String officeAdd2, String officeZip) {
		// TODO Auto-generated method stub
		Statement st;
		ArrayList<Advertisement> advertisementList = new ArrayList<Advertisement>();
		
				try {
					String query = "select * from advertise_car a,employee e "
							+ "where a.start_zip='"+startZip+"' and a.username=e.username";
			String query1 = "select * from advertise_car a,employee e "
					+ "where e.zipcode='"+startZip+"'and a.destination_zip='"+officeZip+"' and a.username=e.username";
			System.out.println(query);
			st = conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next())
			{
				Advertisement advertisement = new Advertisement();
				System.out.println("atleast getting some result while lookup");
				advertisement.setUserName(rs.getString("username")) ;
				advertisement.setCharge(rs.getInt("price")) ;
				advertisement.setCarModel(rs.getString("model"));
				advertisement.setCapacity(rs.getInt("capacity"));
				advertisement.setRequired(rs.getInt("required"));
				advertisement.setStartStAdd(rs.getString("start_street"));
				advertisement.setStartCity(rs.getString("start_city"));
				advertisement.setStartZip(rs.getString("start_zip"));
				advertisement.setDestination(rs.getString("destination"));
				advertisement.setDestinationZip(rs.getString("destination_zip"));
				advertisement.setMessage(rs.getString("message"));
				advertisement.setDate(rs.getString("date"));
				advertisement.setTime(rs.getString("time"));
				advertisement.setLatitute(rs.getDouble("latitude"));
				advertisement.setLongitude(rs.getDouble("longitude"));
				advertisementList.add(advertisement);
				
				
				
				
				//advertisement.setUserName(rs.getString("username")) ;
				//advertisement.setUserName(rs.getString("username")) ;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return advertisementList;
	}
}

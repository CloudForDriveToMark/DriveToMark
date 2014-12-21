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

	public Connection dbConnect() {
		Connection conn = null;
		String url = "jdbc:mysql://twit.cqlk5i2ycxba.us-east-1.rds.amazonaws.com:3306/driveToMark";
		// String dbName = "twitter_sentiment";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "us341";
		String password = "sandy2203";

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println(conn);
			// conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

	public String authenticateUser(Connection conn, String userName,
			String password) {
		// TODO Auto-generated method stub
		String name = "";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st
					.executeQuery("select * from employee where username='"
							+ userName + "'and password='" + password + "'");
			if (rs.next()) {
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
		try {
			PreparedStatement ps = conn
					.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, employee.getUserName());
			ps.setString(2, employee.getPassword());
			ps.setString(3, employee.getName());
			ps.setString(4, employee.getStAdd());
			ps.setString(5, employee.getCity());
			ps.setString(6, employee.getState());
			ps.setString(7, employee.getCountry());
			ps.setString(8, employee.getZipCode());
			ps.setString(9, employee.getContact());
			t = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return t;

	}

	public int createAdvertisement(Connection conn, Advertisement advertisement) {
		// TODO Auto-generated method stub
		int t = 0;

		// calculate lat and long
		double latitude = 0;
		double longitude = 0;
		double destLat = 0;
		double destLng = 0;
		AddressConverter converter = new AddressConverter();
		try {
			GoogleResponse resStart = converter.convertToLatLong(advertisement
					.getStartStAdd()
					+ " "
					+ advertisement.getStartCity()
					+ " "
					+ advertisement.getStartZip());
			GoogleResponse resDest = converter
					.convertToLatLong(advertisement.getDestination() + " "
							+ advertisement.getDestinationZip());
			if (resStart.getStatus().equals("OK")) {
				for (Result result : resStart.getResults()) {

					latitude = Double.parseDouble(result.getGeometry()
							.getLocation().getLat());
					longitude = Double.parseDouble(result.getGeometry()
							.getLocation().getLng());
					System.out.println("Lattitude of address is :"
							+ result.getGeometry().getLocation().getLat());
					System.out.println("Longitude of address is :"
							+ result.getGeometry().getLocation().getLng());
					System.out.println("Location is "
							+ result.getGeometry().getLocation_type());
				}
			} else {
				System.out.println(resStart.getStatus());
			}
			if (resDest.getStatus().equals("OK")) {
				for (Result result : resDest.getResults()) {

					destLat = Double.parseDouble(result.getGeometry()
							.getLocation().getLat());
					destLng = Double.parseDouble(result.getGeometry()
							.getLocation().getLng());
					System.out.println("Lattitude of address is :"
							+ result.getGeometry().getLocation().getLat());
					System.out.println("Longitude of address is :"
							+ result.getGeometry().getLocation().getLng());
					System.out.println("Location is "
							+ result.getGeometry().getLocation_type());
				}
			} else {
				System.out.println(resDest.getStatus());
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement ps = conn
					.prepareStatement("insert into advertise_car values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, null);
			ps.setString(2, advertisement.getUserName());
			ps.setString(3, advertisement.getCarModel());
			ps.setInt(4, advertisement.getCapacity());
			ps.setInt(5, advertisement.getRequired());
			ps.setInt(6, advertisement.getCharge());
			ps.setString(7, advertisement.getStartStAdd());
			ps.setString(8, advertisement.getStartCity());
			ps.setString(9, advertisement.getStartZip());
			ps.setString(10, advertisement.getDestination());
			ps.setString(11, advertisement.getDestinationZip());
			ps.setString(12, advertisement.getMessage());
			ps.setString(13, advertisement.getDate());
			ps.setString(14, advertisement.getTime());
			ps.setDouble(15, latitude);
			ps.setDouble(16, longitude);
			ps.setDouble(17, destLat);
			ps.setDouble(18, destLng);

			t = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return t;
	}

	/*
	 * public ArrayList<Advertisement> lookUp(Connection conn, String startCity,
	 * String startZip, String people, String officeAdd, String officeAdd2,
	 * String officeZip) { // TODO Auto-generated method stub Statement st;
	 * ArrayList<Advertisement> advertisementList = new
	 * ArrayList<Advertisement>();
	 * 
	 * try { String query = "select * from advertise_car a,employee e " +
	 * "where a.start_zip='"+startZip+"' and a.username=e.username"; String
	 * query1 = "select * from advertise_car a,employee e " +
	 * "where e.zipcode='"
	 * +startZip+"'and a.destination_zip='"+officeZip+"' and a.username=e.username"
	 * ; System.out.println(query); st = conn.createStatement(); ResultSet
	 * rs=st.executeQuery(query);
	 * 
	 * while(rs.next()) { Advertisement advertisement = new Advertisement();
	 * System.out.println("atleast getting some result while lookup");
	 * advertisement.setUserName(rs.getString("username")) ;
	 * advertisement.setCharge(rs.getInt("price")) ;
	 * advertisement.setCarModel(rs.getString("model"));
	 * advertisement.setCapacity(rs.getInt("capacity"));
	 * advertisement.setRequired(rs.getInt("required"));
	 * advertisement.setStartStAdd(rs.getString("start_street"));
	 * advertisement.setStartCity(rs.getString("start_city"));
	 * advertisement.setStartZip(rs.getString("start_zip"));
	 * advertisement.setDestination(rs.getString("destination"));
	 * advertisement.setDestinationZip(rs.getString("destination_zip"));
	 * advertisement.setMessage(rs.getString("message"));
	 * advertisement.setDate(rs.getString("date"));
	 * advertisement.setTime(rs.getString("time"));
	 * advertisement.setLatitute(rs.getDouble("latitude"));
	 * advertisement.setLongitude(rs.getDouble("longitude"));
	 * advertisement.setDestLat(rs.getDouble("destLat"));
	 * advertisement.setDestLng(rs.getDouble("destLng"));
	 * System.out.println("destination long lat are : "
	 * +advertisement.getDestLat()+" "+advertisement.getDestLng());
	 * advertisementList.add(advertisement);
	 * 
	 * 
	 * 
	 * 
	 * //advertisement.setUserName(rs.getString("username")) ;
	 * //advertisement.setUserName(rs.getString("username")) ;
	 * 
	 * } } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return advertisementList; }
	 */

	public ArrayList<Advertisement> lookUpDefault(Connection conn,
			String userName) {
		// TODO Auto-generated method stub
		Statement st;
		ArrayList<Advertisement> advertisementList = new ArrayList<Advertisement>();

		try {
			String query = "select * from advertise_car a,employee e "
					+ "where e.username='" + userName
					+ "' and a.start_zip=e.zipcode";

			System.out.println(query);
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Advertisement advertisement = new Advertisement();
				System.out.println("atleast getting some result while lookup");
				advertisement.setUserName(rs.getString("username"));
				advertisement.setCharge(rs.getInt("price"));
				advertisement.setCarModel(rs.getString("model"));
				advertisement.setCapacity(rs.getInt("capacity"));
				advertisement.setRequired(rs.getInt("required"));
				advertisement.setStartStAdd(rs.getString("start_street"));
				advertisement.setStartCity(rs.getString("start_city"));
				advertisement.setStartZip(rs.getString("start_zip"));
				advertisement.setDestination(rs.getString("destination"));
				advertisement
						.setDestinationZip(rs.getString("destination_zip"));
				advertisement.setMessage(rs.getString("message"));
				advertisement.setDate(rs.getString("date"));
				advertisement.setTime(rs.getString("time"));
				advertisement.setLatitute(rs.getDouble("latitude"));
				advertisement.setLongitude(rs.getDouble("longitude"));
				advertisement.setDestLat(rs.getDouble("destLat"));
				advertisement.setDestLng(rs.getDouble("destLng"));
				System.out.println("destination long lat are : "
						+ advertisement.getDestLat() + " "
						+ advertisement.getDestLng());
				advertisementList.add(advertisement);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return advertisementList;

	}

	public ArrayList<Advertisement> lookUpWithFilter(Connection conn,
			String userName, int people, int distance, String startAddress,
			String startZip, String destinationAddress) {
		// TODO Auto-generated method stub
		Statement st;
		AddressConverter converter = new AddressConverter();
		ArrayList<Advertisement> advertisementList = new ArrayList<Advertisement>();
		StringBuilder query = null;
		double[] userLatLong = null;
		// if user has specified startAddress as filter then find this address's
		// latitude and longitude otherwise lookup with default home location
		if (startZip != null && startAddress!=null) {
			query = new StringBuilder(
					"select * from advertise_car a where a.start_city= '"
							+ startAddress + "'");
			userLatLong = getLatLongFromAddess(conn, startAddress + startZip);
		} else {
			query = new StringBuilder(
					"select * from advertise_car a,employee e where e.username='"
							+ userName + "' and a.start_zip=e.zipcode");
			userLatLong = getLatLongFromUserName(conn, userName);
		}

		try {

			if (people != 0) {
				query = query.append(" and a.required>=" + people + "");
				System.out.println(query);
			}
			if (destinationAddress != null) {
				query = query.append(" and a.destination like '%"
						+ destinationAddress + "%'");
			}
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query.toString());

			while (rs.next()) {
				Advertisement advertisement = new Advertisement();
				System.out.println("atleast getting some result while lookup");
				advertisement.setUserName(rs.getString("username"));
				advertisement.setCharge(rs.getInt("price"));
				advertisement.setCarModel(rs.getString("model"));
				advertisement.setCapacity(rs.getInt("capacity"));
				advertisement.setRequired(rs.getInt("required"));
				advertisement.setStartStAdd(rs.getString("start_street"));
				advertisement.setStartCity(rs.getString("start_city"));
				advertisement.setStartZip(rs.getString("start_zip"));
				advertisement.setDestination(rs.getString("destination"));
				advertisement
						.setDestinationZip(rs.getString("destination_zip"));
				advertisement.setMessage(rs.getString("message"));
				advertisement.setDate(rs.getString("date"));
				advertisement.setTime(rs.getString("time"));
				advertisement.setLatitute(rs.getDouble("latitude"));
				advertisement.setLongitude(rs.getDouble("longitude"));
				advertisement.setDestLat(rs.getDouble("destLat"));
				advertisement.setDestLng(rs.getDouble("destLng"));
				System.out.println("destination long lat are : "
						+ advertisement.getDestLat() + " "
						+ advertisement.getDestLng());
				if (distance == 0) {
					advertisementList.add(advertisement);
				} else if (distance > 0
						&& converter.distanceNew(advertisement.getLatitute(),
								advertisement.getLongitude(), userLatLong[0],
								userLatLong[1]) <= distance) {
					advertisementList.add(advertisement);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return advertisementList;

	}

	private double[] getLatLongFromAddess(Connection conn, String address) {
		// TODO Auto-generated method stub
		double latLong[] = new double[2];
		AddressConverter converter = new AddressConverter();
		try {
			GoogleResponse resStart = converter.convertToLatLong(address);
			if (resStart.getStatus().equals("OK")) {
				for (Result result : resStart.getResults()) {
					latLong[0] = Double.parseDouble(result.getGeometry()
							.getLocation().getLat());
					latLong[1] = Double.parseDouble(result.getGeometry()
							.getLocation().getLng());
				}
			} else {
				System.out.println(resStart.getStatus());
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return latLong;
	}

	private double[] getLatLongFromUserName(Connection conn, String userName) {
		// TODO Auto-generated method stub
		double latLong[] = new double[2];
		Statement st;
		AddressConverter converter = new AddressConverter();
		String query = "select * from employee e where e.username='" + userName
				+ "' ";
		String address = "";
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query.toString());

			if (rs.next()) {
				address = rs.getString("street") + " " + rs.getString("city")
						+ rs.getString("zipcode");
				System.out.println("address is " + address);
			}

			try {
				GoogleResponse resStart = converter.convertToLatLong(address);
				if (resStart.getStatus().equals("OK")) {
					for (Result result : resStart.getResults()) {

						latLong[0] = Double.parseDouble(result.getGeometry()
								.getLocation().getLat());
						latLong[1] = Double.parseDouble(result.getGeometry()
								.getLocation().getLng());
						System.out.println("Lattitude of address is :"
								+ result.getGeometry().getLocation().getLat());
						System.out.println("Longitude of address is :"
								+ result.getGeometry().getLocation().getLng());
						System.out.println("Location is "
								+ result.getGeometry().getLocation_type());
					}
				} else {
					System.out.println(resStart.getStatus());
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return latLong;

	}
}

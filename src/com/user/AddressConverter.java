package com.user;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class AddressConverter {
	/*
	 * Geocode request URL. Here see we are passing "json" it means we will get
	 * the output in JSON format. You can also pass "xml" instead of "json" for
	 * XML output. For XML output URL will be
	 * "http://maps.googleapis.com/maps/api/geocode/xml";
	 */

	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

	/*
	 * Here the fullAddress String is in format like
	 * "address,city,state,zipcode". Here address means "street number + route"
	 * .
	 */
	public GoogleResponse convertToLatLong(String fullAddress)
			throws IOException {

		/*
		 * Create an java.net.URL object by passing the request URL in
		 * constructor. Here you can see I am converting the fullAddress String
		 * in UTF-8 format. You will get Exception if you don't convert your
		 * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
		 * parameter we also need to pass "sensor" parameter. sensor (required
		 * parameter) ó Indicates whether or not the geocoding request comes
		 * from a device with a location sensor. This value must be either true
		 * or false.
		 */
		URL url = new URL(URL + "?address="
				+ URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
		// Open the Connection
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(in,
				GoogleResponse.class);
		in.close();
		return response;

	}

	public GoogleResponse convertFromLatLong(String latlongString)
			throws IOException {

		/*
		 * Create an java.net.URL object by passing the request URL in
		 * constructor. Here you can see I am converting the fullAddress String
		 * in UTF-8 format. You will get Exception if you don't convert your
		 * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
		 * parameter we also need to pass "sensor" parameter. sensor (required
		 * parameter) ó Indicates whether or not the geocoding request comes
		 * from a device with a location sensor. This value must be either true
		 * or false.
		 */
		URL url = new URL(URL + "?latlng="
				+ URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");
		// Open the Connection
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(in,
				GoogleResponse.class);
		in.close();
		return response;

	}

	// Distance Finder Code

	public double distance(double lat1, double lon1, double lat2, double lon2,
			String string) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		
			dist = dist * 1.609344 * 0.621371;
		
		return (dist);
	}
	public double distanceNew(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344 * 0.621371;
		System.out.println("distance calculated: "+ dist );
		return (dist);
	}

	public double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public static void main(String[] args) throws IOException {

		AddressConverter addrObj = new AddressConverter();
		double lat1 = 0, lat2 = 0, lng1 = 0, lng2 = 0;
		GoogleResponse res = addrObj.convertToLatLong("45 Beacon Avenue");
		if (res.getStatus().equals("OK")) {
			for (Result result : res.getResults()) {

				lat1 = Double.parseDouble(result.getGeometry().getLocation()
						.getLat());
				lng1 = Double.parseDouble(result.getGeometry().getLocation()
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

		// 2nd address

		GoogleResponse res2 = addrObj
				.convertToLatLong("Apple Store upper west side");
		if (res.getStatus().equals("OK")) {
			for (Result result : res2.getResults()) {
				lat2 = Double.parseDouble(result.getGeometry().getLocation()
						.getLat());
				lng2 = Double.parseDouble(result.getGeometry().getLocation()
						.getLng());
				System.out.println("Lattitude of address is :"
						+ result.getGeometry().getLocation().getLat());
				System.out.println("Longitude of address is :"
						+ result.getGeometry().getLocation().getLng());
				System.out.println("Location is "
						+ result.getGeometry().getLocation_type());
			}
		} else {
			System.out.println(res2.getStatus());
		}
		// Calculating distance between locations;
		System.out.println(addrObj.distance(lat1, lng1, lat2, lng2, "K")
				+ " Kilometers\n");

		System.out.println("\n");
		GoogleResponse res1 = new AddressConverter()
				.convertFromLatLong("18.92038860,72.83013059999999");
		if (res1.getStatus().equals("OK")) {
			for (Result result : res1.getResults()) {
				System.out.println("address is :"
						+ result.getFormatted_address());
			}
		} else {
			System.out.println(res1.getStatus());
		}

	}
}
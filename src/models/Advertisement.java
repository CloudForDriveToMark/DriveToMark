package models;

public class Advertisement {

	private int advertisementId;
	private String userName;
	private String carModel;
	private int capacity;
	private int required;
	private int charge;
	private String startStAdd;
	private String startCity;
	private String startZip;
	private String destination;
	private String destinationZip;
	private String message;
	private String date;
	private String time;
	private double latitute;
	private double longitude;
	private double destLat;
	private double destLng;
	
	public Advertisement(String userName, String carModel, String capacity, String required,
			String charge,String startStAdd,String startCity,String startZip,
			String officeAdd, String officeZip, String message, String date, String time) {
		// TODO Auto-generated constructor stub
		this.userName = userName;
		this.carModel = carModel;
		this.capacity = Integer.parseInt(capacity);
		this.required = Integer.parseInt(required);
		this.charge = Integer.parseInt(charge);
		this.startStAdd = startStAdd;
		this.startCity = startCity;
		this.startZip = startZip;
		this.destination = officeAdd;
		this.destinationZip = officeZip;
		this.message = message;
		this.date = date;
		this.time = time;
		
	}
	
	
	public int getAdvertisementId() {
		return advertisementId;
	}


	public void setAdvertisementId(int advertisementId) {
		this.advertisementId = advertisementId;
	}


	public double getDestLat() {
		return destLat;
	}


	public void setDestLat(double destLat) {
		this.destLat = destLat;
	}


	public double getDestLng() {
		return destLng;
	}


	public void setDestLng(double destLng) {
		this.destLng = destLng;
	}


	public double getLatitute() {
		return latitute;
	}


	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public int getRequired() {
		return required;
	}


	public void setRequired(int required) {
		this.required = required;
	}


	public String getStartStAdd() {
		return startStAdd;
	}

	public void setStartStAdd(String startStAdd) {
		this.startStAdd = startStAdd;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getStartZip() {
		return startZip;
	}

	public void setStartZip(String startZip) {
		this.startZip = startZip;
	}

	public String getDestinationZip() {
		return destinationZip;
	}

	public void setDestinationZip(String destinationZip) {
		this.destinationZip = destinationZip;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Advertisement() {
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	

}

package models;

public class Employee {
	private String  userName;
	private String  password;
	private String  name;
	private String  contact;
	private String  stAdd;
	private String  city;
	private String  state;
	private String  country;
	private String  zipCode;
	public Employee(String userName, String password, String name,
			String contact, String stAdd, String city,String country, String state,
			String zipCode) {
		// TODO Auto-generated constructor stub
		this.userName = userName;
		this.password = password;
		this.name = name;
		this .contact = contact;
		this.stAdd = stAdd;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getStAdd() {
		return stAdd;
	}
	public void setStAdd(String stAdd) {
		this.stAdd = stAdd;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
}

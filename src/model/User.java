package model;

public class User {
	private int userID;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private Cart userCart = new Cart();
	
	public User() {}
	
	public User(int userID, String name, String address, String city,
			String state, String zip, String phone) {
		this.userID = userID;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Cart getUserCart() {
		return userCart;
	}
	public void setUserCart(Cart userCart) {
		this.userCart = userCart;
	}
	
	
}

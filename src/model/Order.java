package model;

import java.util.ArrayList;

public class Order {
	private int orderID;
	private int userID;
	private ArrayList<OrderItem> items;
	private String status;

	public Order() {
		
	}

	public Order(int orderID, int userID) {
	    this.orderID = orderID;
	    this.userID = userID;
	}

	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public ArrayList<OrderItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}

	public void setStatus(String theStatus) {
		this.status = theStatus;
	}
	
	public String getStatus(){
		return this.status;
	}

}

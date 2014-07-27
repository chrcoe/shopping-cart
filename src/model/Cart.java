package model;

import java.util.ArrayList;

public class Cart {
	private int cartID;
	private int userID;
	private ArrayList<CartItem> items = new ArrayList<CartItem>();
	
	private double cartTotal;
	private double shippingCost;
	
	public Cart() {}
	
	public Cart(int cartID, int userID, double cartTotal) {
		this.cartID = cartID;
		this.userID = userID;
		this.cartTotal = cartTotal;
	}
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public double getCartTotal() {
		return cartTotal;
	}
	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public ArrayList<CartItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}
	
}

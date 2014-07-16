package model;

import java.util.ArrayList;

public class Cart {
	private int cartID;
	private int userID;
	private ArrayList<CartItem> items;
	
	private double cartTotal;
	
	public Cart() {
		
	}
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
	
}

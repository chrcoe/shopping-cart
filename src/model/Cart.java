package model;

import java.util.ArrayList;

public class Cart {
	private int cartID;
	private int userID;
	private ArrayList<CartItem> items = new ArrayList<CartItem>();
	
	private double shippingCost;
	
	public Cart() {}
	
	public Cart(int cartID, int userID) {
		this.cartID = cartID;
		this.userID = userID;
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
		double cartTotal = 0.0;
		for(CartItem ci : getItems()){
			cartTotal += ci.getLinePrice();
		}
		return cartTotal;
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

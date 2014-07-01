package model;

public class CartItem {
	int cartItemID;
	int productID;
	int cartID;
	int quantity;
	double linePrice;
	
	public CartItem() {
		
	}
	public CartItem(int cartItemID, int productID, int cartID, int quantity,
			double linePrice) {
		this.cartItemID = cartItemID;
		this.productID = productID;
		this.cartID = cartID;
		this.quantity = quantity;
		this.linePrice = linePrice;
	}
	public int getCartItemID() {
		return cartItemID;
	}
	public void setCartItemID(int cartItemID) {
		this.cartItemID = cartItemID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getLinePrice() {
		return linePrice;
	}
	public void setLinePrice(double linePrice) {
		this.linePrice = linePrice;
	}
	
}

package model;

public class CartItem {
	private int cartItemID;
	private Product product;
	private int cartID;
	private int quantity;
	private double linePrice;
	
	public CartItem() {
		
	}
	public CartItem(int cartItemID, Product product, int cartID, int quantity,
			double linePrice) {
		this.cartItemID = cartItemID;
		this.product = product;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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

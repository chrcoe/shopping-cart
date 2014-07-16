package model;

public class OrderItem {
	private int orderItemID;
	private Product product;
	private int orderID;
	private int quantity;
	private double linePrice;
	
	public OrderItem() {
		
	}
	public OrderItem(int orderItemID, Product product, int orderID, int quantity,
			double linePrice) {
		this.orderItemID = orderItemID;
		this.product = product;
		this.orderID = orderID;
		this.quantity = quantity;
		this.linePrice = linePrice;
	}
	public int getOrderItemID() {
		return orderItemID;
	}
	public void setOrderItemID(int orderItemID) {
		this.orderItemID = orderItemID;
	}
	public Product getProduct() {
		return product;
	}
	public void setProductID(Product product) {
		this.product = product;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
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

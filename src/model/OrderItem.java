package model;

public class OrderItem {
	private int orderItemID;
	private int productID;
	private int orderID;
	private int quantity;
	private double linePrice;

	public OrderItem() {

	}
	public OrderItem(int orderItemID, int productID, int orderID, int quantity,
			double linePrice) {
		this.orderItemID = orderItemID;
		this.productID = productID;
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
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
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

package test.view;

import model.Product;

public class ProductsTest {
	private Product item;
	private Product[] allProducts;
	
	public ProductsTest() {
		Product p1 = new Product(12, "Swiss", "The finest Swiss Cheese from the finest Swiss cows. No additives or preservatives are used in creating this delicious cheese.", "Cheese", 4.99, 10, 2, 3, false, "./images/swiss_cheese_small.png");
		Product p2 = new Product(16, "American", "The finest American Cheese from the finest American cows. No additives or preservatives are used in creating this delicious cheese.", "Cheese", 1.99, 2, 0, 1, true, "./images/american_cheese_small.png");
		Product p3 = new Product(25, "Jeans", "Comfortable and stylish. A perfect fit for working around the ranch, or for a night on the town.", "Clothes", 24.99, 1, 10, 2, false, "./images/jeans_small.png");
		item = p1;
		allProducts = new Product[]{p1, p2, p3};
	}
	
	public Product getItem() {
		return item;
	}
	public void setItem(Product item) {
		this.item = item;
	}
	public Product[] getAllProducts() {
		return allProducts;
	}
	public void setAllProducts(Product[] allProducts) {
		this.allProducts = allProducts;
	}
}

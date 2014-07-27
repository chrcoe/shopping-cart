package action;

import java.sql.SQLException;

import javax.naming.NamingException;

import dao.ProductDAO;
import test.view.ProductsTest;
import model.Product;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

public class ProductActionBean implements ActionBean{
	
	private Product[] products = new Product[]{};
	private Product item = null;
	private int itemID;
	private String categoryName;
	private String[] categoryNames = new String[]{};

	private CartAppActionBeanContext ctx;
	
	@Override
	public CartAppActionBeanContext getContext() {
		return ctx;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.ctx = (CartAppActionBeanContext)context;
	}
	
	@HandlesEvent("getCategories")
	@DefaultHandler
    public Resolution getCategories() {
		try {
			this.categoryNames = new dao.ProductDAO().getCategoryList().toArray(this.categoryNames);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return new ForwardResolution("/categories.jsp");
    }
	
	@HandlesEvent("getProducts")
    public Resolution getCategoryProducts() {
		//ProductsTest pt = new ProductsTest();
		try {
			this.products = new dao.ProductDAO().getProductsByCategoryName(this.categoryName).toArray(this.products);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return new ForwardResolution("/products.jsp");
    }
	
	@HandlesEvent("showDetail")
	public Resolution getProduct(){
		try {
			this.item = new dao.ProductDAO().getProductByProductID(itemID);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ForwardResolution("/productdetail.jsp");
	}

	public Product[] getProducts(){
		return this.products;
	}

	public Product getItem() {
		return item;
	}

	public void setItemId(String itemId) {
		itemID = Integer.parseInt(itemId);
		try {
			item = new ProductDAO().getProductByProductID(itemID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("setting item id to ["+itemId+"]");
	}

	public String[] getCategoryNames() {
		return categoryNames;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}

package action;

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
	
	private Product[] allProducts = null;
	private Product item = null;
	private int itemID;

	private CartAppActionBeanContext ctx;
	
	@Override
	public CartAppActionBeanContext getContext() {
		return ctx;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.ctx = (CartAppActionBeanContext)context;
	}
	
	@DefaultHandler
    public Resolution getAll() {
		ProductsTest pt = new ProductsTest();
		this.allProducts = pt.getAllProducts();
        return new ForwardResolution("/products.jsp");
    }
	
	@HandlesEvent("showDetail")
	public Resolution getProduct(){
		ProductsTest pt = new ProductsTest();
		this.item = pt.getItem(itemID);
		return new ForwardResolution("/productdetail.jsp");
	}

	public Product[] getAllProducts() {
		return allProducts;
	}

	public Product getItem() {
		return item;
	}

	public void setItemId(String itemId) {
		itemID = Integer.parseInt(itemId);
		//item = new ProductDAO().getProductByProductID(itemID);
		System.out.println("setting item id to ["+itemId+"]");
	}

//	public void setAllProducts(Product[] allProducts) {
//		this.allProducts = allProducts;
//	}

}

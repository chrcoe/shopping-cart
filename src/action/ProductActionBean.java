package action;

import model.Product;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class ProductActionBean implements ActionBean{
	private ActionBeanContext ctx;
	private Product[] allProducts = null;
	private Product item = null;

	@Override
	public ActionBeanContext getContext() {
		return ctx;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.ctx = context;
	}
	
	@DefaultHandler
    public Resolution getAll() {
		this.allProducts = new Product[]{new Product(1234, "name", "category", 9.99, 20, 0, 2, false)};
        return new ForwardResolution("/products.jsp");
    }
	
	public Resolution getProduct(){
		this.item = new Product(1234, "name", "category", 9.99, 20, 0, 2, false);
		return new ForwardResolution("/productdetail.jsp");
	}

	public Product[] getAllProducts() {
		return allProducts;
	}

	public Product getItem() {
		return item;
	}

	public void setItemId(String itemId) {
		System.out.println("setting item id to ["+itemId+"]");
	}

//	public void setAllProducts(Product[] allProducts) {
//		this.allProducts = allProducts;
//	}

}

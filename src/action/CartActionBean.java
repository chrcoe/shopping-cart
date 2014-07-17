package action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

public class CartActionBean implements ActionBean{
	
	private int productId;

	private CartAppActionBeanContext ctx;
	
	@Override
	public CartAppActionBeanContext getContext() {
		return ctx;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.ctx = (CartAppActionBeanContext)context;
	}
	
	@HandlesEvent("AddToCart")
	public Resolution addToCart(){
		return new ForwardResolution("/cart.jsp");
	}
	
	@HandlesEvent("UpdateCart")
	public Resolution updateCart(){
		
		return new ForwardResolution("/cart.jsp");
	}
	
	@HandlesEvent("RemoveFromCart")
	public Resolution removeFromCart(){
		
		return new ForwardResolution("/cart.jsp");
	}

	@HandlesEvent("CheckOut")
	public Resolution checkOut(){
		
		return new ForwardResolution("/cart.jsp");
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}

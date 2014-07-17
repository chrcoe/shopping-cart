package action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

public class CartActionBean implements ActionBean{
	private int productId;

	@Override
	public ActionBeanContext getContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContext(ActionBeanContext arg0) {
		// TODO Auto-generated method stub
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}

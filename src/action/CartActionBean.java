package action;

import policy.TransactionPolicy;
import business.UnitOfWork;
import business.exceptions.PolicyException;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

public class CartActionBean implements ActionBean{
	
	private int productId;
	private int quantity;

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
		//this.ctx.getUser().getCart().addItem(this.getProductId(),this.getQuantity());
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
		TransactionPolicy policyGraph = (TransactionPolicy) this.ctx.getServletContext().getAttribute("AppPolicy");
		UnitOfWork checkout = UnitOfWork.create(business.CheckOut.class, policyGraph);
		try {
			checkout.Go();
		} catch (PolicyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ForwardResolution("/cart.jsp");
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

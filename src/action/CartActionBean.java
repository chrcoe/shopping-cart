package action;

import java.sql.SQLException;

import javax.naming.NamingException;

import policy.TransactionPolicy;
import business.UnitOfWork;
import business.UnitOfWork.ICallBackDelegate;
import business.exceptions.PolicyException;
import business.exceptions.UserNotRegisteredException;
import model.Cart;
import model.CartItem;
import model.Product;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

public class CartActionBean implements ActionBean {

	private int itemId;
	private int quantity;

	private CartAppActionBeanContext ctx;

	@Override
	public CartAppActionBeanContext getContext() {
		return ctx;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.ctx = (CartAppActionBeanContext) context;
	}
	
	@HandlesEvent("ShowCart")
	public Resolution showCart(){
		return new ForwardResolution("/cart.jsp");
	}

	@HandlesEvent("AddToCart")
	public Resolution addToCart() {
		TransactionPolicy policyGraph = (TransactionPolicy) this.ctx.getServletContext().getAttribute(CartAppActionBeanContext.policyAttribute);
		UnitOfWork updateCart = UnitOfWork.create(business.UpdateCart.class,policyGraph).with(ctx.getUser()).using(new ICallBackDelegate() {

			@Override
			public void execute() {
				Product p;
				try {
					p = new dao.ProductDAO().getProductByProductID(itemId);
					ctx.getUser().getUserCart().getItems().add(new CartItem(p, quantity));
				} catch (SQLException | NamingException e) {
					// handle error and pass to page
					e.printStackTrace();
				}
			}
		});
		try {
			updateCart.Go();
		} catch (PolicyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ForwardResolution("/cart.jsp");
	}

	@HandlesEvent("UpdateCart")
	public Resolution updateCart() {

		return new ForwardResolution("/cart.jsp");
	}

	@HandlesEvent("RemoveFromCart")
	public Resolution removeFromCart() {
		TransactionPolicy policyGraph = (TransactionPolicy) this.ctx.getServletContext().getAttribute("AppPolicy");
		UnitOfWork updateCart = UnitOfWork.create(business.UpdateCart.class, policyGraph).using(new ICallBackDelegate() {

			@Override
			public void execute() {
				for (CartItem ci : ctx.getUser().getUserCart().getItems()) {
					if (ci.getProduct().getProductID() == itemId) {
						ctx.getUser().getUserCart().getItems().remove(ci);
						break;
					}
				}
			}
		});
		try {
			updateCart.Go();
		} catch (PolicyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ForwardResolution("/cart.jsp");
	}

	@HandlesEvent("CheckOut")
	public Resolution checkOut() {
		TransactionPolicy policyGraph = (TransactionPolicy) this.ctx.getServletContext().getAttribute(CartAppActionBeanContext.policyAttribute);
		UnitOfWork checkout = UnitOfWork.create(business.CheckOut.class, policyGraph).with(ctx.getUser());
		try {
			checkout.Go();
		} catch (UserNotRegisteredException e){
			String message = "Must be registered to check out";
			return new ForwardResolution("/login.jsp");
		} catch (PolicyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ForwardResolution("/confirmpurchase.jsp");
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int productId) {
		this.itemId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getUserCart(){
		return ctx.getUser().getUserCart();
	}
}

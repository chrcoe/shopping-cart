package business;

import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.NamingException;

import dao.OrderDAO;
import business.exceptions.CheckOutException;
import business.exceptions.InventoryConsistancyException;
import business.exceptions.InventoryUpdateException;
import business.exceptions.PolicyException;
import model.Cart;
import model.CartItem;
import model.OrderItem;
import model.User;

public class CheckOut extends UnitOfWork {
	private CheckOut(){}

	@SuppressWarnings("unchecked")
	@Override
	protected void execute() throws PolicyException {
		// NOTE: if we get this far, then the policy rule has validated and reserved quantities
		model.User u = (User) ctx.get(model.User.class);
		if(u.getUserCart().getItems().size()>0){	
			Hashtable<Integer,Integer> reservations = new Hashtable<Integer,Integer>();
			reservations = (Hashtable<Integer, Integer>) ctx.get(reservations.getClass());
			try {
				//create order
				dao.OrderDAO odao = new dao.OrderDAO();
				int oid = odao.createOrderByUserID(u.getUserID());
				model.Order o = odao.getOrderByOrderID(oid);

				dao.OrderItemDAO oidao = new dao.OrderItemDAO();
				for(CartItem ci:u.getUserCart().getItems()){
					//create order item
					OrderItem newOrderItem = new model.OrderItem(0, ci.getProduct().getProductID(), oid, ci.getQuantity(), ci.getLinePrice());
					oidao.createOrderItem(newOrderItem);
				}
				
				o.setStatus("Complete");
				odao.updateOrder(oid, o);
				// clear cart
				u.setUserCart(new Cart());
				// update inventory and release reservations 
				try {
					business.ProductGateway.Reconcile(reservations);
				} catch (InventoryConsistancyException
						| InventoryUpdateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				// release all previous reservations
				business.ProductGateway.Release(reservations);
				throw new CheckOutException("Error occured during checkout; no items purchased.");
			}
		}
	}
}

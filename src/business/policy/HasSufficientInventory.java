package business.policy;

import java.util.Hashtable;

import model.CartItem;
import model.User;
import business.Context;
import business.exceptions.InsufficientInventoryException;
import business.exceptions.PolicyException;

public class HasSufficientInventory extends Policy {

	@Override
	public void rule(Context context) throws PolicyException, InsufficientInventoryException {
		User u = (User) context.get(User.class);
		Hashtable<Integer,Integer> reservations = new Hashtable<Integer,Integer>();
		for(CartItem ci:u.getUserCart().getItems()){
			try {
				business.ProductGateway.Reserve(ci.getProduct().getProductID(), ci.getQuantity());
				reservations.put(ci.getProduct().getProductID(), ci.getQuantity());
			} catch (InsufficientInventoryException e) {
				//release previous reservations 
				business.ProductGateway.Release(reservations);
				throw e;
			}
		}
		context.put(reservations);
	}

}

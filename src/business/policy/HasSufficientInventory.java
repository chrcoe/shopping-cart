package business.policy;

import model.User;
import business.Context;
import business.exceptions.PolicyException;

public class HasSufficientInventory extends Policy {

	@Override
	public void preCheck(Context context) throws PolicyException {
		User u = (User) context.get(User.class);
		/* 
		for each cartitem in user.cart
			check cartitem count against inventory
		
		*/
	}

	@Override
	public void postCheck(Context context) throws PolicyException {
		// TODO Auto-generated method stub

	}

}

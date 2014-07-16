package business.policy;

import model.User;
import business.Context;
import business.exceptions.PolicyException;
import business.exceptions.UserNotRegisteredException;

public class Shipping extends Policy {

	@Override
	public void preCheck(Context context) throws PolicyException {
		policy.Policy polInfo = super.getPolicyInfo();
		double rate = 0.0;
		double premiumRate=0.0;
		for(policy.Parameter parm:polInfo.getParameter()){
			switch (parm.getId()) {
			case "Rate":
				rate = Double.parseDouble(parm.getValue());
				break;
			case "PremiumRate":
				premiumRate = Double.parseDouble(parm.getValue());
				break;
			default:
				break;
			}
		}
		User user = (User)context.get(User.class);
		// double shipping = 0.0;
		// double cartTotal = 0.0;
		// cart = user.getCart();
		// for(CartItem ci : cart){
		//    cartTotal += ci.getUnitCost()*ci.getQuantity();
		// shipping = cartTotal * policyRate
		// cart.setShipping(shipping);
	}

	@Override
	public void postCheck(Context context) throws PolicyException {
		// TODO Auto-generated method stub

	}

}

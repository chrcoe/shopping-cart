package business.policy;

import dao.OrderDAO;
import model.User;
import business.Context;
import business.exceptions.PolicyException;

public class Shipping extends Policy {

	@Override
	public void preCheck(Context context) throws PolicyException {

	}

	@Override
	public void postCheck(Context context) throws PolicyException {
		policy.Policy polInfo = super.getPolicyInfo();
		double rate = 0.0;
		double premiumRate = 0.0;
		int premiumMinOrder = Integer.MAX_VALUE;
		for (policy.Parameter parm : polInfo.getParameter()) {
			switch (parm.getId()) {
			case "Rate":
				rate = Double.parseDouble(parm.getValue());
				break;
			case "PremiumRate":
				premiumRate = Double.parseDouble(parm.getValue());
				break;
			case "PremiumMinOrders":
				premiumMinOrder = Integer.parseInt(parm.getValue());
			default:
				break;
			}
		}
		//User user = (User) context.get(User.class);
		//dao.OrderDAO o = new dao.OrderDAO();
		double cartTotal = 0.0;
		// cart = user.getCart();
		// for(CartItem ci : cart){
		// cartTotal += ci.getUnitCost()*ci.getQuantity();
		//double shipping = cartTotal * (o.getOrdersByUserID(user.getUserID()).size()>=premiumMinOrder?premiumRate:rate);
		// cart.setShipping(shipping);
	}

}

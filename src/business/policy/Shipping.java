package business.policy;

import java.sql.SQLException;

import javax.naming.NamingException;

import model.User;
import business.Context;
import business.exceptions.PolicyException;

public class Shipping extends Policy {

	private boolean isPremium = false;

	@Override
	public void rule(Context context) throws PolicyException {
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
		User user = (User) context.get(User.class);
		dao.OrderDAO o;
		try {
			o = new dao.OrderDAO();
			isPremium  = o.getOrdersByUserID(user.getUserID()).size()>=premiumMinOrder;
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double shipping = user.getUserCart().getCartTotal() * (isPremium?premiumRate:rate);
		user.getUserCart().setShippingCost(shipping);	
	}

}

package business.policy;

import java.sql.SQLException;

import javax.naming.NamingException;

import policy.Parameter;
import model.User;
import business.Context;
import business.exceptions.PolicyException;

public class UpdateProfileStatus extends Policy {

	@Override
	public void rule(Context context) throws PolicyException {
		User u = (User) context.get(User.class);
		Boolean isPremium = false;
		int minPremium = Integer.MAX_VALUE;
		for (Parameter pa : super.getPolicyInfo().getParameter()) {
			if ("MinOrders".equalsIgnoreCase(pa.getId())) {
				minPremium = Integer.parseInt(pa.getValue());
				break;
			}
		}
		if (minPremium == Integer.MAX_VALUE) {
			throw new PolicyException("Missing Policy Parameter [MinOrders]");
		}
		dao.OrderDAO o;
		try {
			o = new dao.OrderDAO();
			isPremium = (o.getOrdersByUserID(u.getUserID()).size() >= minPremium);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (isPremium) {
			// set premium user status
			// new dao.UserDAO().getUserByUserID(u.getUserID()).set
		}
	}

}

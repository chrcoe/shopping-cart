package action;

import policy.Policy;
import model.User;
import net.sourceforge.stripes.action.ActionBeanContext;

public class CartAppActionBeanContext extends ActionBeanContext {
	public static final String userAttribute = "USER";
	public static final String policyAttribute = "POLICY";

	public void setUser(User user) {
		getRequest().getSession().setAttribute(userAttribute, user);
	}

	public User getUser() {
		return (User) getRequest().getSession().getAttribute(userAttribute);
	}

	public policy.Policy getAppPolicy() {
		return (Policy) this.getServletContext().getAttribute(policyAttribute);
	}
}

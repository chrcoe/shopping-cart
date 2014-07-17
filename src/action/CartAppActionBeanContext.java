package action;

import model.User;
import net.sourceforge.stripes.action.ActionBeanContext;

public class CartAppActionBeanContext extends ActionBeanContext {
	 public void setUser(User user) {
	        getRequest().getSession().setAttribute("user", user);
	    }

	    public User getUser() {
	        return (User) getRequest().getSession().getAttribute("user");
	    }
}

package action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

public class ProfileActionBean implements ActionBean {

	private CartAppActionBeanContext ctx;
	private int userID;
	private String pwd;
	
	@Override
	public CartAppActionBeanContext getContext() {
		return ctx;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.ctx = (CartAppActionBeanContext)context;
	}
	
	@HandlesEvent("LogIn")
	public Resolution logIn(){
		dao.UserDAO udao= new dao.UserDAO();
		this.ctx.setUser(udao.getUserByUserID(getUserID()));
		return new ForwardResolution("/");
	}
	
	@HandlesEvent("LogOut")
	public Resolution logOut(){
		this.ctx.setUser(null);
		return new ForwardResolution("/");
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}

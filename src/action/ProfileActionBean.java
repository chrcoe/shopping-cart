package action;

import java.sql.SQLException;

import javax.naming.NamingException;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

public class ProfileActionBean implements ActionBean {

	private CartAppActionBeanContext ctx;
	private String userName;
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
		dao.UserDAO udao;
		try {
			udao = new dao.UserDAO();
			this.ctx.setUser(udao.getUserByName(this.getUserName()));
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//show user error
		}
		return new ForwardResolution("/");
	}

	@HandlesEvent("LogOut")
	public Resolution logOut(){
		this.ctx.setUser(null);
		return new ForwardResolution("/");
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

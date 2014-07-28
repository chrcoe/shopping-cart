package action;

import java.sql.SQLException;

import javax.naming.NamingException;

import model.Order;
import model.User;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

public class ProfileActionBean implements ActionBean {

	private CartAppActionBeanContext ctx;
	private String userName;
	private String pwd;
	private Order[] orders = new Order[]{};

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
			User u = udao.getUserByName(this.userName);
			if(ctx.getUser()!=null){
				u.setUserCart(ctx.getUser().getUserCart());
				ctx.getUser().setUserCart(null);
			}
			this.ctx.setUser(u);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.ctx.setUser(null);
			//show user error
		}
		return new ForwardResolution("/");
	}

	@HandlesEvent("LogOut")
	public Resolution logOut(){
		this.ctx.setUser(new model.User());
		return new ForwardResolution("/");
	}
	
	@HandlesEvent("AccountDetail")
	public Resolution accountDetail(){
		return new ForwardResolution("/account.jsp");
	}

	@HandlesEvent("OrderHistory")
	public Resolution orderHistory(){
		Resolution r = new ForwardResolution("/");
		if(ctx.getUser()!=null){
			try {
				this.orders = new dao.OrderDAO().getOrdersByUserID(ctx.getUser().getUserID()).toArray(this.orders);
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			r = new ForwardResolution("/orderhistory.jsp");
		}
		return r;
	}

	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserName() {
		return ctx.getUser()==null?"Not Logged In":ctx.getUser().getName();
	}

	public String getAddress(){
		return ctx.getUser()==null?"":ctx.getUser().getAddress();
	}
	
	public String getCity(){
		return ctx.getUser()==null?"":ctx.getUser().getCity();
	}
	
	public String getState(){
		return ctx.getUser()==null?"":ctx.getUser().getState();
	}

	public String getZip(){
		return ctx.getUser()==null?"":ctx.getUser().getZip();
	}

	public String getPhone(){
		return ctx.getUser()==null?"":ctx.getUser().getPhone();
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Order[] getOrders() {
		return orders;
	}

	public void setOrders(Order[] orders) {
		this.orders = orders;
	}

}

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
	private String email;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private Order[] orders = new Order[]{};
	private String error = null;

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
			if(null != u){
				if(ctx.getUser()!=null){
					u.setUserCart(ctx.getUser().getUserCart());
					ctx.getUser().setUserCart(null);
				}
				this.ctx.setUser(u);
			}else{
				this.error = "Login failed. Check username and password.";
				return new ForwardResolution("/login.jsp");
			}
			
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
	
	@SuppressWarnings("unused")
	@HandlesEvent("Register")
	public Resolution register(){
		User newUser = new User();
		newUser.setName(this.userName);
		newUser.setAddress(address);
		newUser.setCity(city);
		newUser.setPhone(phone);
		newUser.setState(state);
		newUser.setZip(zip);
		
		try {
			int uid = new dao.UserDAO().createUser(newUser);
			return new ForwardResolution("/confirmregistration.jsp");
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ForwardResolution("/register.jsp");
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean getError() {
		return !(error==null);
	}
	
	public String getErrorMessage() {
		return error==null?"":error;
	}

}

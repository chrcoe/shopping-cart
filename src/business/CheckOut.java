package business;

import java.sql.SQLException;

import javax.naming.NamingException;

import model.Cart;
import model.CartItem;
import model.OrderItem;
import model.User;

public class CheckOut extends UnitOfWork {
	private CheckOut(){}

	@Override
	protected void execute() {
		model.User u = (User) ctx.get(model.User.class);
		if(u.getUserCart().getItems().size()>0){
			try {
				int oid = new dao.OrderDAO().createOrderByUserID(u.getUserID());
				dao.OrderItemDAO oidao = new dao.OrderItemDAO();
				for(CartItem ci:u.getUserCart().getItems()){
					OrderItem newOrderItem = new model.OrderItem(0, ci.getProduct().getProductID(), oid, ci.getQuantity(), ci.getLinePrice());
					oidao.createOrderItem(newOrderItem);
				}
				
				//last
				u.setUserCart(new Cart());
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

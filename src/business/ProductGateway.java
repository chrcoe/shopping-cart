package business;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map.Entry;

import javax.naming.NamingException;

import business.exceptions.InsufficientInventoryException;
import business.exceptions.InventoryConsistancyException;
import business.exceptions.InventoryUpdateException;

public class ProductGateway {
	private static Hashtable<Integer,ProductGateway> gwTable = new Hashtable<Integer,ProductGateway>();
	
	private int productId = 0;
	private int reservations = 0;
	
	private ProductGateway(int productId){
		this.productId = productId;
	}
	
	private static ProductGateway getInstance(int productId){
		synchronized (gwTable) {
			if (!gwTable.containsKey(productId)) {
				gwTable.put(productId, new ProductGateway(productId));
			}
		}
		return gwTable.get(productId);
	}
	
	public static void Reserve(int productId, int quantity) throws InsufficientInventoryException{
		ProductGateway.getInstance(productId).Reserve(quantity);
	}
	
	private synchronized void Reserve(int quantity) throws InsufficientInventoryException{
		//no other action allowed on this instance while reserving
		if(this.getCount() < quantity){
			throw new InsufficientInventoryException("Cannot reserve itemID["+this.productId+"] for quantity["+quantity+"]");
		}
		this.reservations += quantity;
	}
	
	private synchronized int getCount(){
		/* go get it from the database while the object is locked */
		return getInventory() - this.reservations; // return the difference while the object is locked
	}
	
	private int getInventory(){
		/* this is the real inventory (from the database) */
		try {
			return new dao.ProductDAO().getProductByProductID(productId).getUnitsInStock();
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} //this.inventory;
	}
	
	private synchronized void Reconcile(int cartQuantity) throws InventoryConsistancyException, InventoryUpdateException{
		if((this.getCount()+cartQuantity) < cartQuantity){
			throw new InventoryConsistancyException();
		}
		try {
			this.updateInventory(getInventory()-cartQuantity);
		} catch (SQLException e) {
			throw new InventoryUpdateException("No changes made to Inventory or Reservations [PID:"+this.productId+"]");
		}
		this.reservations -= cartQuantity;
	}

	private void updateInventory(int i) throws SQLException {
		/* update the database while the object is locked */
		dao.ProductDAO pd;
		try {
			pd = new dao.ProductDAO();
			model.Product p = pd.getProductByProductID(productId);
			p.setUnitsInStock(i);
			pd.updateProduct(p.getProductID(), p);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static void Release(Hashtable<Integer, Integer> reservations) {
		for(Entry<Integer, Integer> ent:reservations.entrySet()){
			getInstance(ent.getKey()).reservations -= ent.getValue();
		}
	}

	public static void Reconcile(Hashtable<Integer, Integer> reservations) throws InventoryConsistancyException, InventoryUpdateException {
		for(Entry<Integer, Integer> ent:reservations.entrySet()){
			getInstance(ent.getKey()).Reconcile(ent.getValue());
		}
	}
	
	
}

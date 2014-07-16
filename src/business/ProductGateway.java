package business;

import java.sql.SQLException;
import java.util.Hashtable;

import business.exceptions.InsufficientInventoryException;
import business.exceptions.InventoryConsistancyException;
import business.exceptions.InventoryUpdateException;

public class ProductGateway {
	private static Hashtable<Integer,ProductGateway> gwTable = new Hashtable<Integer,ProductGateway>();
	
	private int productId = 0;
	private int inventory = 20;
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
			throw new InsufficientInventoryException();
		}
		this.reservations += quantity;
	}
	
	private synchronized int getCount(){
		/* go get it from the database while the object is locked */
		return getInventory() - this.reservations; // return the difference while the object is locked
	}
	
	private int getInventory(){
		/* this is the real inventory (from the database) */
		return this.inventory;
	}
	
	private synchronized void Reconcile(int cartQuantity) throws InventoryConsistancyException, InventoryUpdateException{
		if(this.getCount() < cartQuantity){
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
		
	}
}

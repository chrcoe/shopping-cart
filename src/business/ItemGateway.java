package business;

import java.sql.SQLException;
import java.util.Hashtable;

public class ItemGateway {
	private static Hashtable<Integer,ItemGateway> itemTable = new Hashtable<Integer,ItemGateway>();
	
	private int itemId = 0;
	private int inventory = 20;
	private int reservations = 0;
	
	private ItemGateway(int itemId){
		this.itemId = itemId;
	}
	
	private static ItemGateway getInstance(int itemId){
		if(!itemTable.containsKey(itemId)){
			itemTable.put(itemId, new ItemGateway(itemId));
		}
		return itemTable.get(itemId);
	}
	
	public static void Reserve(int itemId, int quantity){
	
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
			throw new InventoryUpdateException("No changes made to Inventory or Reservations [PID:"+this.itemId+"]");
		}
		this.reservations -= cartQuantity;
	}

	private void updateInventory(int i) throws SQLException {
		/* update the database while the object is locked */
		
	}
}

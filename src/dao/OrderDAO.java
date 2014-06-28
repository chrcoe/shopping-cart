package dao;

import java.util.ArrayList;
import model.Order;

public class OrderDAO {
    public Order getOrderByOrderID(int orderID) {
//        TODO: implement this method
//        would populate userID from SQL result
        int userID = -1;
        return new Order(orderID, userID);
    }
    public ArrayList<Order> getAllOrdersByUserID(int userID) {
//        TODO: implement this method
//        populate list of userOrders from SQL resultset
        ArrayList<Order> userOrders = null;
        return userOrders;
    }
}

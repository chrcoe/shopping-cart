package dao;

import java.util.ArrayList;

import model.Order;

public class OrderDAO {

    // TODO: setup constructors to handle connection pooling

    // CREATE
    public Order createOrderByUserID(int userID) {
        // TODO: implement this method
        int orderID = -1; // need to use the lastAutoKey idea here
        return new Order(orderID, userID);
    }

    // RETRIEVE
    public Order getOrderByOrderID(int orderID) {
        // TODO: implement this method
        // would populate userID from SQL result
        int userID = -1;
        return new Order(orderID, userID);
    }

    public ArrayList<Order> getAllOrdersByUserID(int userID) {
        // TODO: implement this method
        // populate list of userOrders from SQL resultset
        ArrayList<Order> userOrders = null;
        return userOrders;
    }

    // UPDATE
    public void updateOrder(Order theOrder) {
        // TODO: implement this method
    }

    // DELETE
    public void removeOrder(Order theOrder) {
        // TODO: implement this method
    }
}

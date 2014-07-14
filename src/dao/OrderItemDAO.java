package dao;

import java.util.ArrayList;

import model.OrderItem;

public class OrderItemDAO {

    // TODO: setup constructors to handle connection pooling

    // CREATE
    public OrderItem createOrderItemByOrderID(int orderID) {
        // TODO: implement this method
        int orderItemID = -1; // need to use the lastAutoKey idea here
        int productID = -1;
        int quantity = -1;
        double linePrice = -1.1;
        return new OrderItem(orderItemID, productID, orderID, quantity,
                linePrice);
    }

    // RETRIEVE
    public ArrayList<OrderItem> getOrderItemsByOrderID(int orderID) {
        // TODO: implement this method
        int orderItemID = -1;
        int productID = -1;
        int quantity = -1;
        double linePrice = -1.1;

        // populate list from SQL resultset
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(new OrderItem(orderItemID, productID, orderID, quantity,
                linePrice));

        return orderItems;
    }

    public ArrayList<OrderItem> getOrderItemsByProductID(int productID) {
        // TODO: implement this method
        int orderItemID = -1;
        int orderID = -1;
        int quantity = -1;
        double linePrice = -1.1;

        // populate list from SQL resultset
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(new OrderItem(orderItemID, productID, orderID, quantity,
                linePrice));

        return orderItems;
    }

    // UPDATE
    public void updateOrderItem(OrderItem theOrderItem) {
        // TODO: implement this method
    }

    // DELETE
    public void removeOrderItem(OrderItem theOrderItem) {
        // TODO: implement this method
    }
}

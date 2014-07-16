package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.OrderItem;
import model.Product;

public class OrderItemDAO {

    private Connection con;

    public OrderItemDAO() throws NamingException, SQLException {

        // initialize lastAutoKeys here

        Context cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/CartDB" );

        con = ds.getConnection();

    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    // CREATE
    public OrderItem createOrderItemByOrderID(int orderID) {
        // TODO: implement this method
        int orderItemID = -1; // need to use the lastAutoKey idea here
        Product product = null;
        int quantity = -1;
        double linePrice = -1.1;
        return new OrderItem(orderItemID, product, orderID, quantity,
                linePrice);
    }

    // RETRIEVE
    public ArrayList<OrderItem> getOrderItemsByOrderID(int orderID) {
        // TODO: implement this method
        int orderItemID = -1;
        Product product = null;
        int quantity = -1;
        double linePrice = -1.1;

        // populate list from SQL resultset
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(new OrderItem(orderItemID, product, orderID, quantity,
                linePrice));

        return orderItems;
    }

    public ArrayList<OrderItem> getOrderItemsByProductID(Product product) {
        // TODO: implement this method
        int orderItemID = -1;
        int orderID = -1;
        int quantity = -1;
        double linePrice = -1.1;

        // populate list from SQL resultset
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(new OrderItem(orderItemID, product, orderID, quantity,
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

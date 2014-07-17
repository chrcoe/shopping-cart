package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.OrderItem;

public class OrderItemDAO {

    private Connection con;
    private int lastOrderItemAutoKey;

    public OrderItemDAO() throws NamingException, SQLException {

        // initialize lastAutoKeys here
        lastOrderItemAutoKey = -1;

        Context cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/CartDB");

        con = ds.getConnection();
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    // CREATE
    public int createOrderItem(OrderItem newOrderItem) throws SQLException {
        // need the productID that this order item contains (FK)
        // as well as the orderID that it will be attached to (FK)

        // linePrice is a calculated value that can be retrieved by:
        // product.getUnitPrice() * newOrderItem.getQuantity()
        // or by using a join statement in the DB as such, this value is not
        // persisted in the database

        String sql = "INSERT INTO cart_comp461_db.OrderItem (idOrderItem, "
                + "idOrder, idProduct, quantity) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        ps.setNull(1, java.sql.Types.INTEGER);
        ps.setInt(2, newOrderItem.getOrderID());
        ps.setInt(3, newOrderItem.getProductID());
        ps.setInt(4, newOrderItem.getQuantity());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.last();
        lastOrderItemAutoKey = rs.getInt(1);

        return lastOrderItemAutoKey;
    }

    // RETRIEVE
    public OrderItem getOrderItemByOrderItemID(int orderItemID)
            throws SQLException {
        // not sure how useful this will be, but it is here in case it comes in
        // handy

        // this will return the calculated lineprice by using a join statement

        String sql = "SELECT lft.idOrderItem,lft.idOrder,lft.idProduct,"
                + "lft.quantity,rgt.price * lft.quantity AS lineprice"
                + "FROM cart_comp461_db.OrderItem lft "
                + "JOIN cart_comp461_db.Product rgt "
                + "ON lft.idProduct = rgt.idProduct"
                + "WHERE lft.idOrderItem = 1" + orderItemID;

        OrderItem record = null;
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

        int orderItemID_, productID, orderID, quantity;
        double linePrice;

        while (rs.next()) {
            orderItemID_ = rs.getInt("idOrderItem");
            productID = rs.getInt("idProduct");
            orderID = rs.getInt("idOrder");
            quantity = rs.getInt("quantity");
            linePrice = rs.getDouble("lineprice");

            record = new OrderItem(orderItemID_, productID, orderID, quantity,
                    linePrice);
        }

        return record;
    }

    public ArrayList<OrderItem> getOrderItemsByOrderID(int orderID)
            throws SQLException {

        String sql = "SELECT lft.idOrderItem,lft.idOrder,lft.idProduct,"
                + "lft.quantity,rgt.price * lft.quantity AS lineprice"
                + "FROM cart_comp461_db.OrderItem lft "
                + "JOIN cart_comp461_db.Product rgt "
                + "ON lft.idProduct = rgt.idProduct WHERE lft.idOrder = "
                + orderID;

        ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

        int orderItemID, productID, orderID_, quantity;
        double linePrice;

        while (rs.next()) {
            orderItemID = rs.getInt("idOrderItem");
            productID = rs.getInt("idProduct");
            orderID = rs.getInt("idOrder");
            quantity = rs.getInt("quantity");
            linePrice = rs.getDouble("lineprice");

            OrderItem record = new OrderItem(orderItemID, productID, orderID,
                    quantity, linePrice);

            orderItemList.add(record);
        }

        return orderItemList;

    }

    public ArrayList<OrderItem> getOrderItemsByProductID(int productID)
            throws SQLException {
        String sql = "SELECT lft.idOrderItem,lft.idOrder,lft.idProduct,"
                + "lft.quantity,rgt.price * lft.quantity AS lineprice"
                + "FROM cart_comp461_db.OrderItem lft "
                + "JOIN cart_comp461_db.Product rgt "
                + "ON lft.idProduct = rgt.idProduct WHERE lft.idProduct = "
                + productID;

        ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

        int orderItemID, productID_, orderID, quantity;
        double linePrice;

        while (rs.next()) {
            orderItemID = rs.getInt("idOrderItem");
            productID_ = rs.getInt("idProduct");
            orderID = rs.getInt("idOrder");
            quantity = rs.getInt("quantity");
            linePrice = rs.getDouble("lineprice");

            OrderItem record = new OrderItem(orderItemID, productID_, orderID,
                    quantity, linePrice);

            orderItemList.add(record);
        }

        return orderItemList;
    }

    // UPDATE
    public void updateOrderItem(OrderItem theOrderItem) throws SQLException {
        String sql = "UPDATE cart_comp461_db.OrderItem SET "
                + "idOrder = ?, idProduct = ?, quantity = ? "
                + "WHERE idOrderItem = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, theOrderItem.getOrderID());
        ps.setInt(2, theOrderItem.getProductID());
        ps.setInt(3, theOrderItem.getQuantity());
        ps.setInt(4, theOrderItem.getOrderItemID());
        ps.executeUpdate();

        ps.close();
    }

    // DELETE
    public void removeOrderItem(OrderItem theOrderItem) throws SQLException {
        String sql = "DELETE FROM cart_comp461_db.OrderItem WHERE idOrderItem = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, theOrderItem.getOrderItemID());

        ps.execute();
        ps.close();
    }

    public void removeOrderItemByOrderItemID(int orderItemID)
            throws SQLException {
        String sql = "DELETE FROM cart_comp461_db.OrderItem WHERE idOrderItem = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, orderItemID);

        ps.execute();
        ps.close();
    }
}

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

import model.Order;
import model.User;
import dao.UserDAO;

public class OrderDAO {

    private Connection con;
    private int lastOrderAutoKey;

    public OrderDAO() throws NamingException, SQLException {

        // initialize lastAutoKeys here
        lastOrderAutoKey = -1;

        Context cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/CartDB");

        con = ds.getConnection();
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    // CREATE
    public int createOrderByUserID(int userID) throws SQLException {
        String sql = "INSERT INTO cart_comp461_db.Order (idOrder, idUser) "
                + "VALUES (?, ?)";

        ResultSet rs = null;

        PreparedStatement ps = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        ps.setNull(1, java.sql.Types.INTEGER);
        ps.setInt(2, userID);

        ps.executeUpdate();
        rs = ps.getGeneratedKeys();
        rs.last();
        lastOrderAutoKey = rs.getInt(1);

        return lastOrderAutoKey;
    }

    // RETRIEVE
    public Order getOrderByOrderID(int orderID) throws SQLException {
        Order record = null;
        String sql = "SELECT * FROM cart_comp461_db.Order "
                + "WHERE idOrder = " + orderID;

        // prepared statement
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

        int orderID_, userID;

        while (rs.next()) {
            orderID_ = rs.getInt("idOrder");
            userID = rs.getInt("idUser");

            record = new Order(orderID_, userID);
        }

        s.close();
        rs.close();

        return record;
    }

    public ArrayList<Order> getAllOrdersByUserID(int userID)
            throws SQLException {
        String sql = "SELECT * FROM cart_comp461_db.Order WHERE " + "idUser = "
                + userID;

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

        ArrayList<Order> orderList = new ArrayList<Order>();
        int orderID, usrID;

        while (rs.next()) {
            orderID = rs.getInt("idOrder");
            usrID = rs.getInt("idUser");

            Order newOrder = new Order(orderID, usrID);

            orderList.add(newOrder);
        }

        return orderList;
    }

    // do we really need this? - not implementing any tests for this yet
    public User getUserByOrderID(int orderID) throws SQLException, NamingException {
        UserDAO uDAO = new UserDAO();
        User newUser = uDAO.getUserByUserID(this.getOrderByOrderID(orderID).getUserID());
        uDAO.closeConnection();
        return newUser;
    }

    // UPDATE
    public void updateOrder(int orderID, Order theOrder) throws SQLException {
        // UPDATE <table> SET <column>=<value> WHERE ID=<id>

        String sql = "UPDATE cart_comp461_db.Order SET "
                + "idUser = ? WHERE idOrder = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, orderID);
        ps.setInt(2, theOrder.getOrderID());

        ps.executeUpdate();

        ps.close();
    }

    // DELETE
    public void removeOrder(Order theOrder) throws SQLException {
        String sql = "DELETE FROM cart_comp461_db.Order WHERE idOrder = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, theOrder.getOrderID());

        ps.execute();
        ps.close();
    }

    public void removeOrderByOrderID(int orderID) throws SQLException {
        String sql = "DELETE FROM cart_comp461_db.Order WHERE idOrder = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, orderID);

        ps.execute();
        ps.close();
    }
}

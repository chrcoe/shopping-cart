package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.User;

public class UserDAO {

    private Connection con;
    private int lastUserAutoKey;

    public UserDAO() throws NamingException, SQLException {

        // initialize lastAutoKeys here
        lastUserAutoKey = -1;

        Context cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/CartDB");

        con = ds.getConnection();
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    // CREATE
    public int createUser(User newUser) throws SQLException {

        String sql = "INSERT INTO cart_comp461_db.User (idUser, name,"
                + "address, city, state, zip, phone) VALUES"
                + "(?, ?, ?, ?, ?, ?, ?)";

        ResultSet rs = null;

        PreparedStatement ps = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        ps.setNull(1, java.sql.Types.INTEGER);
        ps.setString(2, newUser.getName());
        ps.setString(3, newUser.getAddress());
        ps.setString(4, newUser.getCity());
        ps.setString(5, newUser.getState());
        ps.setString(6, newUser.getZip());
        ps.setString(7, newUser.getPhone());

        ps.executeUpdate();
        rs = ps.getGeneratedKeys();
        rs.last();
        lastUserAutoKey = rs.getInt(1);

        return lastUserAutoKey;
    }

    // RETRIEVE
    /**
     * @param userID
     *            the ID of the user to lookup
     * @return the User Model object, or null if not found
     * @throws SQLException
     *             when SQL error occurs
     */
    public User getUserByUserID(int userID) throws SQLException {

        User record = null;
        String sql = "SELECT * FROM cart_comp461_db.User " + "WHERE idUser = "
                + userID;

        // prepared statement
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

        int id;
        String name, address, city, state, zip, phone;

        while (rs.next()) {
            id = rs.getInt("idUser");
            name = rs.getString("name");
            address = rs.getString("address");
            city = rs.getString("city");
            state = rs.getString("state");
            zip = rs.getString("zip");
            phone = rs.getString("phone");

            record = new User(id, name, address, city, state, zip, phone);
        }

        s.close();
        rs.close();

        return record;
    }

    // UPDATE
    public void updateUser(int userId, User theUser) throws SQLException {
        // UPDATE <table> SET <column>=<value> WHERE ID=<id>

        String sql = "UPDATE cart_comp461_db.User SET "
                + "name = ?, address = ?, city = ?, state = ?, zip = ?,"
                + "phone = ? WHERE idUser = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, theUser.getName());
        ps.setString(2, theUser.getAddress());
        ps.setString(3, theUser.getCity());
        ps.setString(4, theUser.getState());
        ps.setString(5, theUser.getZip());
        ps.setString(6, theUser.getPhone());
        ps.setInt(7, userId);
        ps.executeUpdate();

        ps.close();
    }

    // DELETE
    public void removeUser(User theUser) throws SQLException {
        // DELETE FROM cart_comp461_db.User WHERE ID = <id>

        String sql = "DELETE FROM cart_comp461_db.User WHERE idUser = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, theUser.getUserID());

        ps.execute();
        ps.close();
    }

    public void removeUserByUserID(int userID) throws SQLException {
        // DELETE FROM cart_comp461_db.User WHERE ID = <id>

        String sql = "DELETE FROM cart_comp461_db.User WHERE idUser = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userID);

        ps.execute();
        ps.close();
    }
}

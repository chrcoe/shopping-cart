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
    public int createUser(User newUser) {
        try {
            Statement s = con.createStatement(
                    java.sql.ResultSet.TYPE_FORWARD_ONLY,
                    java.sql.ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = null;

            // insert into User (NAME,ADDRESS,CITY,STATE,ZIP,PHONE)
            // VALUES (...);

            String sql = String.format(
                    "INSERT INTO cart_comp461_db.User (idUser, name,"
                            + "address, city, state, zip, phone) VALUES"
                            + "(null, \'%s\', \'%s\', \'%s\', \'%s\', \'%s\',"
                            + "\'%s\')", newUser.getName(),
                    newUser.getAddress(), newUser.getCity(),
                    newUser.getState(), newUser.getZip(), newUser.getPhone());

            s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            rs = s.getGeneratedKeys();
            rs.last();
            lastUserAutoKey = rs.getInt(1);

            s.close();
            rs.close();

        }
        catch (SQLException e) {
            System.out.println("Statement failed to execute on DB");
            e.printStackTrace();
        }

        return lastUserAutoKey;
    }

    // RETRIEVE
    public User getUserByUserID(int userID) {

        User record = null;

        try {
            String sql = "SELECT * FROM cart_comp461_db.User "
                    + "where idUser = " + userID;

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
        }
        catch (SQLException e) {
            System.out.println("Statement failed to execute on DB");
            e.printStackTrace();
        }
        return record;
    }

    // UPDATE
    public void updateUser(User theUser) {
        // UPDATE <table> SET <column>=<value> WHERE ID=<id>
        try {
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
            ps.setInt(7,  theUser.getUserID());
            ps.executeUpdate();

            ps.close();

        }
        catch (SQLException e) {
            System.out.println("Statement failed to execute on DB");
            e.printStackTrace();
        }
    }

    // DELETE
    public void removeUser(User theUser) {
        // DELETE FROM cart_comp461_db.User WHERE ID = <id>

        try {
            String sql = "DELETE FROM cart_comp461_db.User WHERE ID = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, theUser.getUserID());

            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println("Statement failed to execute on DB");
            e.printStackTrace();
        }
    }

    public void removeUserByUserID(int userID) {
        // TODO: implement this method
    }
}

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class UserDAO {

    // TODO: figure out singleton DBConnection...
    private Connection con;

    public UserDAO() {

        // initialize lastAutoKeys here

        try {
            con = DBConnector.getConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserDAO(Connection inConn) {

        // initialize lastAutoKeys here

        con = inConn;
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    // CREATE
    public int createUser(User newUser) {
        // the idea here is to pass in all user info by building the object
        // first then this method will return that user's PK in the DB
        // TODO: implement this method
        int userID = -1; // need to use the lastAutoKey idea here
        String name = null;
        String address = null;
        String city = null;
        String state = null;
        String zip = null;
        String phone = null;

        User user = new User(userID, name, address, city, state, zip, phone);

        return user.getUserID();
    }

    // RETRIEVE
    public User getUserByUserID(int userID) {
        // TODO: implement this method
        // would populate fields from SQL result

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
        // TODO: implement this method
    }

    // DELETE
    public void removeUser(User theUser) {
        // TODO: implement this method
    }

    public void removeUserByUserID(int userID) {
        // TODO: implement this method
    }
}

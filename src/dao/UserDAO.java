package dao;

import model.User;

public class UserDAO {

    // TODO: figure out singleton DBConnection...

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
        String name = null;
        String address = null;
        String city = null;
        String state = null;
        String zip = null;
        String phone = null;

        return new User(userID, name, address, city, state, zip, phone);
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

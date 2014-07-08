package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static Connection con;
    private static String url;
    private static String uid;
    private static String pwd;

    public DBConnector(String url, String uid, String pwd) {
        this.url = url;
        this.uid = uid;
        this.pwd = pwd;
    }

    /**
     * This method needs to be called before instantiating a DAO object. This
     * needs to be done from within a servlet so the context can be used to pull
     * the data from web.xml dynamically. Each servlet should have a connect()
     * method which handles making the connection and passing it to the DAO.
     *
     * @return data connection to the DB
     */
    public static Connection getConnection() {
        if (con == null) {
            try {

                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);
                con = DriverManager.getConnection(url, uid, pwd);
            }
            catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
            catch (SQLException sqe) {
                sqe.printStackTrace();
            }
        }
        return con;
    }

    public void closeConnection() {
        try {
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public static Connection getConnection() {
    // if (con == null) {
    // try {
    // Properties p = new Properties();
    // InputStream inSt =
    // DBConnector.class.getClassLoader().getResourceAsStream("/db.info");
    // p.load(inSt);
    // String driver = p.getProperty("driver");
    // String url = p.getProperty("url");
    // String user = p.getProperty("user");
    // String password = p.getProperty("password");
    // Class.forName(driver);
    // con = DriverManager.getConnection(url, user, password);
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // e.printStackTrace();
    // }
    // }
    // return con;
    // }
}

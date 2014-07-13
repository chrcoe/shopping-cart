package test.dao_test;

import static org.junit.Assert.*;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.User;

import org.apache.tomcat.dbcp.dbcp.DataSourceConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import dao.DBConnector;
import dao.UserDAO;

public class UserDAOTest {

    private UserDAO uDAO;
    private DBConnector dbc; // this mimics connecting from a servlet

    private Connection connect() {
        // the following commented code shows an example of how the servlet
        // connect() method should flow.

        // String url = getServletContext().getInitParameter("url");
        // String user_name = getServletContext().getInitParameter("userId");
        // String password = getServletContext().getInitParameter("password");
        // ConnectionMaker conMaker = new ConnectionMaker();
        // conMaker.setUrl(url);
        // conMaker.setPassword(password);
        // conMaker.setUserId(user_name);
        // Connection conn = conMaker.getConnection();
        // return conn;

        // we are mimicking this here:
        String url = "jdbc:mysql://localhost:3306/cart_comp461_db";
        String uid = "comp461";
        String pwd = "comp461&!";
        dbc = new DBConnector(url, uid, pwd);

        return dbc.getConnection();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        // sample used from:
        // https://blogs.oracle.com/randystuph/entry/injecting_jndi_datasources_for_junit
        // adjusted for MySQL .. this is needed to mimic the context passed
        // from the servlet to use Junit properly.
        // rcarver - setup the jndi context and the datasource
        try {
            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

            // Construct DataSource
            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
            // OracleConnectionPoolDataSource ds = new
            // OracleConnectionPoolDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/cart_comp461_db");
            ds.setUser("comp461");
            ds.setPassword("comp461");

            ic.bind("java:/comp/env/jdbc/CartDB", ds);
        }
        catch (NamingException ex) {
            ex.printStackTrace();
        }

    }

    @Before
    public void setUp() throws Exception {
        // uDAO = new UserDAO(connect());
        uDAO = new UserDAO();
    }

    @After
    public void tearDown() throws Exception {
        dbc.closeConnection();
    }

    @Test
    public void test_createUser() throws Exception {
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

    @Test
    public void test_getUserByUserID() throws Exception {
        User testUser = uDAO.getUserByUserID(1);

        assertTrue("ID did not match", testUser.getUserID() == 1);
        assertTrue("NAME did not match",
                testUser.getName().equalsIgnoreCase("John"));
        assertTrue("ADDRESS did not match", testUser.getAddress()
                .equalsIgnoreCase("123 Home Road"));
        assertTrue("CITY did not match",
                testUser.getCity().equalsIgnoreCase("Delaware"));
        assertTrue("STATE did not match",
                testUser.getState().equalsIgnoreCase("OH"));
        assertTrue("ZIP did not match",
                testUser.getZip().equalsIgnoreCase("43015"));
        assertTrue("PHONE did not match",
                testUser.getPhone().equalsIgnoreCase("7401234567"));

    }

    @Test
    public void test_updateUser() throws Exception {
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

    @Test
    public void test_removeUser() throws Exception {
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

    @Test
    public void test_removeUserByUserID() throws Exception {
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

}

package test.dao_test;

import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.User;

import org.apache.naming.java.javaURLContextFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import dao.UserDAO;

public class UserDAOTest {

    private UserDAO uDAO;

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
                    javaURLContextFactory.class.getName());
            // needed to add tomcat-juli.jar because InitialContext uses it for
            // logging .. else all tests would fail with a ClassNotFound Exception
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

            // Construct DataSource
            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
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
        uDAO.closeConnection();
    }

    @Test
    public void test_createUser() throws Exception {
        User newUser = new User(-1, "Jim", "321 Test Road", "Columbus", "OH",
                "43230", "6148881234");
        int userId = uDAO.createUser(newUser);

        User testUser = uDAO.getUserByUserID(userId);

        // the user ID will change every time this test runs, so we can't test
        // it
        // properly.
        // assertTrue("ID did not match", testUser.getUserID() == 2);
        assertTrue("NAME did not match",
                testUser.getName().equalsIgnoreCase("Jim"));
        assertTrue("ADDRESS did not match", testUser.getAddress()
                .equalsIgnoreCase("321 Test Road"));
        assertTrue("CITY did not match",
                testUser.getCity().equalsIgnoreCase("Columbus"));
        assertTrue("STATE did not match",
                testUser.getState().equalsIgnoreCase("OH"));
        assertTrue("ZIP did not match",
                testUser.getZip().equalsIgnoreCase("43230"));
        assertTrue("PHONE did not match",
                testUser.getPhone().equalsIgnoreCase("6148881234"));

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
        User user = new User(-1, "Jim", "321 Test Road", "Columbus", "OH",
                "43230", "6148881234");

        User newUser = uDAO.getUserByUserID(1);
        // check before update
        assertTrue("ID did not match", newUser.getUserID() == 1);
        assertTrue("NAME did not match",
                newUser.getName().equalsIgnoreCase("John"));
        assertTrue("ADDRESS did not match", newUser.getAddress()
                .equalsIgnoreCase("123 Home Road"));
        assertTrue("CITY did not match",
                newUser.getCity().equalsIgnoreCase("Delaware"));
        assertTrue("STATE did not match",
                newUser.getState().equalsIgnoreCase("OH"));
        assertTrue("ZIP did not match",
                newUser.getZip().equalsIgnoreCase("43015"));
        assertTrue("PHONE did not match",
                newUser.getPhone().equalsIgnoreCase("7401234567"));

        uDAO.updateUser(user);
        // check after update
        assertTrue("NAME did not match", user.getName().equalsIgnoreCase("Jim"));
        assertTrue("ADDRESS did not match",
                user.getAddress().equalsIgnoreCase("321 Test Road"));
        assertTrue("CITY did not match",
                user.getCity().equalsIgnoreCase("Columbus"));
        assertTrue("STATE did not match", user.getState()
                .equalsIgnoreCase("OH"));
        assertTrue("ZIP did not match", user.getZip().equalsIgnoreCase("43230"));
        assertTrue("PHONE did not match",
                user.getPhone().equalsIgnoreCase("6148881234"));

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

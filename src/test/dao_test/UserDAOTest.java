package test.dao_test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.User;

import org.apache.naming.java.javaURLContextFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import dao.UserDAO;

public class UserDAOTest {

    private UserDAO uDAO;
    private static InitialContext ic;

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
            // logging .. else all tests would fail with a ClassNotFound
            // Exception
            ic = new InitialContext();

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

    @AfterClass
    public static void tearDownClass() throws Exception {
        // unbind in reverse order
        ic.unbind("java:/comp/env/jdbc/CartDB");
        ic.unbind("java:/comp/env/jdbc");
        ic.unbind("java:/comp/env");
        ic.unbind("java:/comp");
        ic.unbind("java:");
    }

    @Before
    public void setUp() throws Exception {
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

        uDAO.removeUser(testUser);
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

        // add one to update
        User newUser = new User(-1, "John_", "123 Home Road", "Delaware", "OH",
                "43015", "7401234567");
        int userId = uDAO.createUser(newUser);
        newUser = uDAO.getUserByUserID(userId);
        // check before update
        assertTrue("NAME did not match",
                newUser.getName().equalsIgnoreCase("John_"));
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

        uDAO.updateUser(newUser.getUserID(), user);
        User testUser = uDAO.getUserByUserID(newUser.getUserID());
        // check after update
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

        uDAO.removeUser(newUser);
    }

    @Test
    public void test_removeUser() throws Exception {
        User newUser = new User(-1, "Jim", "321 Test Road", "Columbus", "OH",
                "43230", "6148881234");
        int userId = uDAO.createUser(newUser);
        User testUser = uDAO.getUserByUserID(userId);

        // should exist here
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

        uDAO.removeUser(testUser);
        // but not here
        testUser = uDAO.getUserByUserID(userId);
        assertNull("expected no record to be returned (null)", testUser);
    }

    @Test
    public void test_removeUserByUserID() throws Exception {
        User newUser = new User(-1, "Jim", "321 Test Road", "Columbus", "OH",
                "43230", "6148881234");
        int userId = uDAO.createUser(newUser);
        User testUser = uDAO.getUserByUserID(userId);

        // should exist here
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

        uDAO.removeUserByUserID(userId);
        // but not here
        testUser = uDAO.getUserByUserID(userId);
        assertNull("expected no record to be returned (null)", testUser);
    }

}

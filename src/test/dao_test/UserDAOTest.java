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

//import dao.DBConnector;

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
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    javaURLContextFactory.class.getName());
            // needed to add tomcat-juli.jar because InitialContext uses it for
            // logging .. else all tests would fail with a ClassNotFound Exception
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

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

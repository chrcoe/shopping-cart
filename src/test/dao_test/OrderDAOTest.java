package test.dao_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.Order;
import model.User;

import org.apache.naming.java.javaURLContextFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import dao.OrderDAO;
import dao.UserDAO;

public class OrderDAOTest {

    private OrderDAO oDAO;
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
        oDAO = new OrderDAO();
    }

    @After
    public void tearDown() throws Exception {
        oDAO.closeConnection();
    }

    @Test
    public void test_createOrderByUserID() throws Exception {
        UserDAO uDAO = new UserDAO();
        User newUser = new User(-1, "Jim", "321 Test Road", "Columbus", "OH",
                "43230", "6148881234");

        int userId = uDAO.createUser(newUser);
        Order newOrder = new Order(-1, userId);

        int orderId = oDAO.createOrderByUserID(newOrder.getUserID());
        Order testOrder = oDAO.getOrderByOrderID(orderId);

        assertEquals(userId, testOrder.getUserID());

        uDAO.removeUserByUserID(userId);
        uDAO.closeConnection();
        oDAO.removeOrderByOrderID(orderId);

    }

    @Test
    public void test_getOrderByOrderID() throws Exception {
        Order testOrder = oDAO.getOrderByOrderID(1);

        assertEquals(1, testOrder.getOrderID());
        assertEquals(1, testOrder.getUserID());
    }

    @Test
    public void test_getAllOrdersByUserID() throws Exception {
        UserDAO uDAO = new UserDAO();
        User newUser = new User(-1, "Jim", "321 Test Road", "Columbus", "OH",
                "43230", "6148881234");

        int userId = uDAO.createUser(newUser);
        Order ord1 = new Order(-1, userId);
        Order ord2 = new Order(-1, userId);
        Order ord3 = new Order(-1, userId);

        ArrayList<Order> orderList = new ArrayList<Order>();
        orderList.add(ord1);
        orderList.add(ord2);
        orderList.add(ord3);

        for (Order ord : orderList) {
            int id = oDAO.createOrderByUserID(ord.getUserID());
            ord.setOrderID(id);
        }

        ArrayList<Order> orderList1 = oDAO.getOrdersByUserID(userId);
        assertEquals(3, orderList1.size());
        for (Order ord : orderList1) {
            assertEquals(userId, ord.getUserID());

            oDAO.removeOrder(ord); // what if I don't remove this? will cascading work?
            // test that later..
        }

        // clean up
        uDAO.removeUserByUserID(userId);
        uDAO.closeConnection();
    }

    @Test
    public void test_getUserByOrderID() throws Exception {
        // trying to decide if we really need the underlying method
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

    @Test
    public void test_updateOrder() throws Exception {
        Order ord = new Order(-1, 1);

        UserDAO uDAO = new UserDAO();
        User newUser = new User(-1, "Jim", "321 Test Road", "Columbus", "OH",
                "43230", "6148881234");

        // to update an order, we have to add a new User and use it's ID to
        // update the order
        int userId = uDAO.createUser(newUser);
        Order newOrder = new Order(-1, userId);
        int orderId = oDAO.createOrderByUserID(newOrder.getUserID());
        newOrder = oDAO.getOrderByOrderID(orderId);

        // check before update
        assertEquals(userId, newOrder.getUserID());

        // do update
        oDAO.updateOrder(newOrder.getOrderID(), ord);
        // check after update
        assertEquals(userId, newOrder.getUserID());

        // clean up DB
        uDAO.removeUserByUserID(userId);
        oDAO.removeOrder(newOrder);
        uDAO.closeConnection();
    }

    @Test
    public void test_removeOrder() throws Exception {
        UserDAO uDAO = new UserDAO();
        User newUser = new User(-1, "Jim", "321 Test Road", "Columbus", "OH",
                "43230", "6148881234");
        int userId = uDAO.createUser(newUser);
        int orderId = oDAO.createOrderByUserID(userId);
        Order testOrd = oDAO.getOrderByOrderID(orderId);

        // should exist here
        assertEquals(userId, testOrd.getUserID());

        oDAO.removeOrder(testOrd);
        // but not here
        testOrd = oDAO.getOrderByOrderID(orderId);
        assertNull("expected no record to be returned (null)", testOrd);

        // clean up
        uDAO.removeUserByUserID(userId);
        uDAO.closeConnection();

        // what happens if I try to remove it twice ?
//        oDAO.removeOrder(testOrd); // this will fail because testOrd is now null
        oDAO.removeOrderByOrderID(orderId);

    }


    @Test
    public void test_removeOrderByOrderID() throws Exception {
        UserDAO uDAO = new UserDAO();
        User newUser = new User(-1, "Jim", "321 Test Road", "Columbus", "OH",
                "43230", "6148881234");
        int userId = uDAO.createUser(newUser);
        int orderId = oDAO.createOrderByUserID(userId);
        Order testOrd = oDAO.getOrderByOrderID(orderId);

        // should exist here
        assertEquals(userId, testOrd.getUserID());

        oDAO.removeOrderByOrderID(orderId);
        // but not here
        testOrd = oDAO.getOrderByOrderID(orderId);
        assertNull("expected no record to be returned (null)", testOrd);

        // clean up
        uDAO.removeUserByUserID(userId);
        uDAO.closeConnection();

        // what happens if I try to remove it twice ?
        oDAO.removeOrderByOrderID(orderId);

    }
}

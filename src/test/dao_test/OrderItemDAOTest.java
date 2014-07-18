package test.dao_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.Order;
import model.OrderItem;
import model.Product;
import model.User;

import org.apache.naming.java.javaURLContextFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.ProductDAO;
import dao.UserDAO;

public class OrderItemDAOTest {

    private OrderItemDAO oiDAO;
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
        oiDAO = new OrderItemDAO();
    }

    @After
    public void tearDown() throws Exception {
        oiDAO.closeConnection();
    }

    @Test
    public void test_createOrderItemByOrderID() throws Exception {
        // create one of each specifically for this test, then we'll clean it
        OrderDAO oDAO = new OrderDAO();
        ProductDAO pDAO = new ProductDAO();
        UserDAO uDAO = new UserDAO();

        int userID = uDAO.createUser(new User(-1, "Jim", "321 Test Road",
                "Columbus", "OH", "43230", "6148881234"));
        int orderID = oDAO.createOrderByUserID(userID);
        int productID = pDAO.createProduct(new Product(-1, "tacos",
                "tacos desc", "mexican food", 15.99, 5, 10, 5, false));

        int quant = 5;
        int orderItemID = oiDAO.createOrderItem(new OrderItem(-1, productID,
                orderID, quant, -1));

        OrderItem testOrderItem = oiDAO.getOrderItemByOrderItemID(orderItemID);

        double expectedLinePrice = quant
                * pDAO.getProductByProductID(productID).getUnitPrice();

        assertEquals(orderItemID, testOrderItem.getOrderItemID());
        assertEquals(productID, testOrderItem.getProductID());
        assertEquals(orderID, testOrderItem.getOrderID());
        assertEquals(quant, testOrderItem.getQuantity());
        assertEquals(expectedLinePrice, testOrderItem.getLinePrice(), 0.00);

        // clean up
        oiDAO.removeOrderItemByOrderItemID(orderItemID);
        pDAO.removeProductByProductID(productID);
        oDAO.removeOrderByOrderID(orderID);
        uDAO.removeUserByUserID(userID);

        pDAO.closeConnection();
        oDAO.closeConnection();
        uDAO.closeConnection();

    }

    @Test
    public void test_getOrderItemByOrderID() throws Exception {
        OrderItem testOrderItem = oiDAO.getOrderItemByOrderItemID(1);

        // from DB
        double productUnitPrice = 4.99;
        double quant = 3;

        double expectedLinePrice = quant * productUnitPrice;

        assertEquals(1, testOrderItem.getOrderID());
        assertEquals(1, testOrderItem.getProductID());
        assertEquals(3, testOrderItem.getQuantity());
        assertEquals(expectedLinePrice, testOrderItem.getLinePrice(), 0.00);

    }

    @Test
    public void test_getOrderItemsByOrderID() throws Exception {
        // need to add multiple orderitems... realistically these would all be
        // on a single order and would each be a different product.
        // need to create:
        // ONE Order, ONE User, THREE Products, THREE OrderItems
        OrderDAO oDAO = new OrderDAO();
        ProductDAO pDAO = new ProductDAO();
        UserDAO uDAO = new UserDAO();

        int quant = 2;
        double prodPrice = 15.99;
        double expectedLinePrice = quant * prodPrice;

        // ONE User
        int userID = uDAO.createUser(new User(-1, "Jim", "321 Test Road",
                "Columbus", "OH", "43230", "6148881234"));
        // ONE Order
        int orderID = oDAO.createOrderByUserID(userID);
        // THREE Products
        ArrayList<Integer> prodIDs = new ArrayList<Integer>();
        prodIDs.add(pDAO.createProduct(new Product(-1, "tacos", "tacos desc",
                "mexican food", prodPrice, 5, 10, 5, false)));
        prodIDs.add(pDAO.createProduct(new Product(-1, "tacos", "tacos desc",
                "mexican food", prodPrice, 5, 10, 5, false)));
        prodIDs.add(pDAO.createProduct(new Product(-1, "tacos", "tacos desc",
                "mexican food", prodPrice, 5, 10, 5, false)));

        for (int productID : prodIDs) {
            int orderItemID = oiDAO.createOrderItem(new OrderItem(-1,
                    productID, orderID, quant, 0));
        }

        ArrayList<OrderItem> ordItemList = oiDAO
                .getOrderItemsByOrderID(orderID);
        assertEquals(3, ordItemList.size());

        int count = 0;
        for (OrderItem oi : ordItemList) {
            assertEquals(prodIDs.get(count).intValue(), oi.getProductID());
            assertEquals(orderID, oi.getOrderID());
            assertEquals(quant, oi.getQuantity());
            assertEquals(expectedLinePrice, oi.getLinePrice(), 0.00);
            count++;
        }
        // clean up
        for (OrderItem oi : ordItemList) {
            oiDAO.removeOrderItem(oi);
        }
        for (int pi : prodIDs) {
            pDAO.removeProductByProductID(pi);
        }
        oDAO.removeOrderByOrderID(orderID);
        uDAO.removeUserByUserID(userID);

        pDAO.closeConnection();
        oDAO.closeConnection();
        uDAO.closeConnection();

    }

    @Test
    public void test_getOrderItemsByProductID() throws Exception {
        // need multiple order items by product

        // can an order item have more than one product ? YES, as long as they
        // are not on the same order

        // need: TWO Users, TWO Orders, ONE Product, TWO OrderItems
        OrderDAO oDAO = new OrderDAO();
        ProductDAO pDAO = new ProductDAO();
        UserDAO uDAO = new UserDAO();

        // TWO Users
        int userID = uDAO.createUser(new User(-1, "Jim", "321 Test Road",
                "Columbus", "OH", "43230", "6148881234"));
        int userID_ = uDAO.createUser(new User(-1, "Jim", "321 Test Road",
                "Columbus", "OH", "43230", "6148881234"));
        // TWO Orders
        int orderID = oDAO.createOrderByUserID(userID);
        int orderID_ = oDAO.createOrderByUserID(userID_);
        // ONE Product
        double unitPrice = 15.99;
        int productID = pDAO.createProduct(new Product(-1, "tacos",
                "tacos desc", "mexican food", unitPrice, 5, 10, 5, false));

        // TWO OrderItems
        int quant = 1;
        int orderItem = oiDAO.createOrderItem(new OrderItem(-1, productID,
                orderID, quant, -1));
        double expectedLinePrice = quant * unitPrice;

        int quant_ = 2;
        int orderItem_ = oiDAO.createOrderItem(new OrderItem(-1, productID,
                orderID_, quant_, -1));
        double expectedLinePrice_ = quant_ * unitPrice;

        ArrayList<OrderItem> ordItemList = oiDAO
                .getOrderItemsByProductID(productID);
        assertEquals(2, ordItemList.size());

        OrderItem oiZero = ordItemList.get(0);
        OrderItem oiOne = ordItemList.get(1);
        // check first
        assertEquals(orderID, oiZero.getOrderID());
        assertEquals(productID, oiZero.getProductID());
        assertEquals(quant, oiZero.getQuantity());
        assertEquals(expectedLinePrice, oiZero.getLinePrice(), 0.00);
        // check second
        assertEquals(orderID_, oiOne.getOrderID());
        assertEquals(productID, oiOne.getProductID());
        assertEquals(quant_, oiOne.getQuantity());
        assertEquals(expectedLinePrice_, oiOne.getLinePrice(), 0.00);

        // clean up
        oiDAO.removeOrderItemByOrderItemID(orderItem);
        oiDAO.removeOrderItemByOrderItemID(orderItem_);
        pDAO.removeProductByProductID(productID);
        oDAO.removeOrderByOrderID(orderID);
        oDAO.removeOrderByOrderID(orderID_);
        uDAO.removeUserByUserID(userID);
        uDAO.removeUserByUserID(userID_);

        pDAO.closeConnection();
        oDAO.closeConnection();
        uDAO.closeConnection();
    }

    @Test
    public void test_updateOrderItem() throws Exception {
        // need ONE OrderItem, TWO Orders, TWO Products, ONE User, TWO diff
        // quant
        // create the one OrderItem with one set of Product/Order/User/Quant
        // then update the one OrderItem with the OTHER set
        OrderDAO oDAO = new OrderDAO();
        ProductDAO pDAO = new ProductDAO();
        UserDAO uDAO = new UserDAO();

        int userID = uDAO.createUser(new User(-1, "Jim", "321 Test Road",
                "Columbus", "OH", "43230", "6148881234"));
        // first set
        int quant = 2;
        int orderID = oDAO.createOrderByUserID(userID);
        int productID = pDAO.createProduct(new Product(-1, "tacos",
                "tacos desc", "mexican food", 15.99, 5, 10, 5, false));
        double expectedLinePrice = quant
                * pDAO.getProductByProductID(productID).getUnitPrice();
        // second set
        int quant_ = 1;
        int orderID_ = oDAO.createOrderByUserID(userID);
        int productID_ = pDAO.createProduct(new Product(-1, "tacos",
                "tacos desc", "mexican food", 15.99, 5, 10, 5, false));
        double expectedLinePrice_ = quant_
                * pDAO.getProductByProductID(productID).getUnitPrice();

        int orderItemID = oiDAO.createOrderItem(new OrderItem(-1, productID,
                orderID, quant, -1));

        OrderItem testOrderItem = oiDAO.getOrderItemByOrderItemID(orderItemID);

        // should be normal here
        assertEquals(orderID, testOrderItem.getOrderID());
        assertEquals(productID, testOrderItem.getProductID());
        assertEquals(quant, testOrderItem.getQuantity());
        assertEquals(expectedLinePrice, testOrderItem.getLinePrice(), 0.00);

        // then update and check..
        oiDAO.updateOrderItem(orderItemID, new OrderItem(-1, productID_,
                orderID_, quant_, -1));
        testOrderItem = oiDAO.getOrderItemByOrderItemID(orderItemID);

        // should match second set
        assertEquals(orderID_, testOrderItem.getOrderID());
        assertEquals(productID_, testOrderItem.getProductID());
        assertEquals(quant_, testOrderItem.getQuantity());
        assertEquals(expectedLinePrice_, testOrderItem.getLinePrice(), 0.00);

        // clean up
        pDAO.removeProductByProductID(productID);
        pDAO.removeProductByProductID(productID_);
        oDAO.removeOrderByOrderID(orderID);
        oDAO.removeOrderByOrderID(orderID_);
        uDAO.removeUserByUserID(userID);

        pDAO.closeConnection();
        oDAO.closeConnection();
        uDAO.closeConnection();
    }

    @Test
    public void test_removeOrderItem() throws Exception {
        // setup to add OrderItem to DB
        OrderDAO oDAO = new OrderDAO();
        ProductDAO pDAO = new ProductDAO();
        UserDAO uDAO = new UserDAO();

        int userID = uDAO.createUser(new User(-1, "Jim", "321 Test Road",
                "Columbus", "OH", "43230", "6148881234"));
        int orderID = oDAO.createOrderByUserID(userID);
        int productID = pDAO.createProduct(new Product(-1, "tacos",
                "tacos desc", "mexican food", 15.99, 5, 10, 5, false));

        int quant = 5;
        int orderItemID = oiDAO.createOrderItem(new OrderItem(-1, productID,
                orderID, quant, -1));

        OrderItem testOrderItem = oiDAO.getOrderItemByOrderItemID(orderItemID);

        double expectedLinePrice = quant
                * pDAO.getProductByProductID(productID).getUnitPrice();

        // should exist here
        assertEquals(orderID, testOrderItem.getOrderID());
        assertEquals(productID, testOrderItem.getProductID());
        assertEquals(quant, testOrderItem.getQuantity());
        assertEquals(expectedLinePrice, testOrderItem.getLinePrice(), 0.00);

        oiDAO.removeOrderItem(testOrderItem);
        // but not here
        testOrderItem = oiDAO.getOrderItemByOrderItemID(orderItemID);
        assertNull("expected no record to be returned (null)", testOrderItem);

        // clean up
        pDAO.removeProductByProductID(productID);
        oDAO.removeOrderByOrderID(orderID);
        uDAO.removeUserByUserID(userID);

        pDAO.closeConnection();
        oDAO.closeConnection();
        uDAO.closeConnection();

        // try to remove it again, this should not throw exception
        oiDAO.removeOrderItemByOrderItemID(orderItemID);
    }

    @Test
    public void test_removeOrderItemByOrderItemID() throws Exception {
        // setup to add OrderItem to DB
        OrderDAO oDAO = new OrderDAO();
        ProductDAO pDAO = new ProductDAO();
        UserDAO uDAO = new UserDAO();

        int userID = uDAO.createUser(new User(-1, "Jim", "321 Test Road",
                "Columbus", "OH", "43230", "6148881234"));
        int orderID = oDAO.createOrderByUserID(userID);
        int productID = pDAO.createProduct(new Product(-1, "tacos",
                "tacos desc", "mexican food", 15.99, 5, 10, 5, false));

        int quant = 5;
        int orderItemID = oiDAO.createOrderItem(new OrderItem(-1, productID,
                orderID, quant, -1));

        OrderItem testOrderItem = oiDAO.getOrderItemByOrderItemID(orderItemID);

        double expectedLinePrice = quant
                * pDAO.getProductByProductID(productID).getUnitPrice();

        // should exist here
        assertEquals(orderID, testOrderItem.getOrderID());
        assertEquals(productID, testOrderItem.getProductID());
        assertEquals(quant, testOrderItem.getQuantity());
        assertEquals(expectedLinePrice, testOrderItem.getLinePrice(), 0.00);

        oiDAO.removeOrderItemByOrderItemID(orderItemID);
        // but not here
        testOrderItem = oiDAO.getOrderItemByOrderItemID(orderItemID);
        assertNull("expected no record to be returned (null)", testOrderItem);

        // clean up
        pDAO.removeProductByProductID(productID);
        oDAO.removeOrderByOrderID(orderID);
        uDAO.removeUserByUserID(userID);

        pDAO.closeConnection();
        oDAO.closeConnection();
        uDAO.closeConnection();

        // try to remove it again, this should not throw exception
        oiDAO.removeOrderItemByOrderItemID(orderItemID);
    }
}

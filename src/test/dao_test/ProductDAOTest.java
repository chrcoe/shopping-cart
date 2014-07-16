package test.dao_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.Product;

import org.apache.naming.java.javaURLContextFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import dao.ProductDAO;

public class ProductDAOTest {

    private ProductDAO pDAO;
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
        pDAO = new ProductDAO();
    }

    @After
    public void tearDown() throws Exception {
        pDAO.closeConnection();
    }

    @Test
    public void test_createProduct() throws Exception {
        Product newProd = new Product(-1, "tacos", "tacos desc",
                "mexican food", 15.99, 5, 10, 5, false);

        int prodId = pDAO.createProduct(newProd);
        Product testProd = pDAO.getProductByProductID(prodId);

        assertTrue("name did not match", testProd.getProductName()
                .equalsIgnoreCase("tacos"));
        assertTrue("description did not match", testProd.getDescription()
                .equalsIgnoreCase("tacos desc"));
        assertTrue("categoryName did not match", testProd.getCategoryName()
                .equalsIgnoreCase("mexican food"));
        assertTrue("price did not match", testProd.getUnitPrice() == 15.99);
        assertTrue("amt_in_stock did not match",
                testProd.getUnitsInStock() == 5);
        assertTrue("amt_on_order did not match",
                testProd.getUnitsOnOrder() == 10);
        assertTrue("threshold did not match", testProd.getReorderLevel() == 5);
        assertFalse("discontinued flag did not match",
                testProd.isDiscontinued());

        pDAO.removeProduct(testProd);
    }

    @Test
    public void test_getProductByProductID() throws Exception {
        Product testProd = pDAO.getProductByProductID(1);

        assertTrue("ID did not match", testProd.getProductID() == 1);
        assertTrue("name did not match", testProd.getProductName()
                .equalsIgnoreCase("swiss"));
        assertNull("description did not match", testProd.getDescription());
        assertTrue("categoryName did not match", testProd.getCategoryName()
                .equalsIgnoreCase("cheese"));
        assertTrue("price did not match", testProd.getUnitPrice() == 4.99);
        assertTrue("amt_in_stock did not match",
                testProd.getUnitsInStock() == 10);
        assertTrue("amt_on_order did not match",
                testProd.getUnitsOnOrder() == 2);
        assertTrue("threshold did not match", testProd.getReorderLevel() == 3);
        assertFalse("discontinued flag did not match",
                testProd.isDiscontinued());
    }

    @Test
    public void test_getProductsByCategoryName() throws Exception {
        Product prod1 = new Product(-1, "catTest", "catTest",
                "testCategoryNameInProduct", 0.99, 10, 0, 5, false);
        Product prod2 = new Product(-1, "catTest", "catTest",
                "testCategoryNameInProduct", 0.99, 10, 0, 5, false);
        Product prod3 = new Product(-1, "catTest", "catTest",
                "testCategoryNameInProduct", 0.99, 10, 0, 5, false);

        ArrayList<Product> prodList = new ArrayList<Product>();
        prodList.add(prod1);
        prodList.add(prod2);
        prodList.add(prod3);

        for (Product prod : prodList) {
            int id = pDAO.createProduct(prod);
            prod.setProductID(id);
        }

        ArrayList<Product> prodList1 = pDAO
                .getProductsByCategoryName("testCategoryNameInProduct");
        assertEquals(3, prodList1.size());
        for (Product prod : prodList1) {
            assertTrue("name did not match", prod.getProductName()
                    .equalsIgnoreCase("catTest"));
            assertTrue("description did not match", prod.getDescription()
                    .equalsIgnoreCase("catTest"));
            assertTrue("name did not match", prod.getCategoryName()
                    .equalsIgnoreCase("testCategoryNameInProduct"));
            assertEquals(0.99, prod.getUnitPrice(), 0.00);
            assertEquals(10, prod.getUnitsInStock());
            assertEquals(0, prod.getUnitsOnOrder());
            assertEquals(5, prod.getReorderLevel());
            assertFalse(prod.isDiscontinued());

            pDAO.removeProduct(prod);
        }

    }

    @Test
    public void test_updateProduct() throws Exception {
        Product prod = new Product(-1, "tacos", "tacos desc", "mexican food",
                15.99, 5, 10, 5, false);

        // add one to update
        Product newProd = new Product(-1, "swiss", null, "cheese", 4.99, 10, 2,
                3, false);
        int prodId = pDAO.createProduct(newProd);
        newProd = pDAO.getProductByProductID(prodId);

        // check before update
        assertTrue("ID did not match", newProd.getProductID() == prodId);
        assertTrue("name did not match", newProd.getProductName()
                .equalsIgnoreCase("swiss"));
        assertNull("description did not match", newProd.getDescription());
        assertTrue("categoryName did not match", newProd.getCategoryName()
                .equalsIgnoreCase("cheese"));
        assertTrue("price did not match", newProd.getUnitPrice() == 4.99);
        assertTrue("amt_in_stock did not match",
                newProd.getUnitsInStock() == 10);
        assertTrue("amt_on_order did not match", newProd.getUnitsOnOrder() == 2);
        assertTrue("threshold did not match", newProd.getReorderLevel() == 3);
        assertFalse("discontinued flag did not match", newProd.isDiscontinued());

        // update product that has this ID with that Product object
        pDAO.updateProduct(newProd.getProductID(), prod);
        Product testProd = pDAO.getProductByProductID(newProd.getProductID());
        // check after update
        assertTrue("name did not match", testProd.getProductName()
                .equalsIgnoreCase("tacos"));
        assertTrue("description did not match", testProd.getDescription()
                .equalsIgnoreCase("tacos desc"));
        assertTrue("categoryName did not match", testProd.getCategoryName()
                .equalsIgnoreCase("mexican food"));
        assertTrue("price did not match", testProd.getUnitPrice() == 15.99);
        assertTrue("amt_in_stock did not match",
                testProd.getUnitsInStock() == 5);
        assertTrue("amt_on_order did not match",
                testProd.getUnitsOnOrder() == 10);
        assertTrue("threshold did not match", testProd.getReorderLevel() == 5);
        assertFalse("discontinued flag did not match",
                testProd.isDiscontinued());

        pDAO.removeProduct(newProd);
    }

    @Test
    public void test_removeProduct() throws Exception {
        Product newProd = new Product(-1, "tacos", "tacos desc",
                "mexican food", 15.99, 5, 10, 5, false);

        int prodId = pDAO.createProduct(newProd);
        Product testProd = pDAO.getProductByProductID(prodId);

        // should exist here
        assertTrue("name did not match", testProd.getProductName()
                .equalsIgnoreCase("tacos"));
        assertTrue("description did not match", testProd.getDescription()
                .equalsIgnoreCase("tacos desc"));
        assertTrue("categoryName did not match", testProd.getCategoryName()
                .equalsIgnoreCase("mexican food"));
        assertTrue("price did not match", testProd.getUnitPrice() == 15.99);
        assertTrue("amt_in_stock did not match",
                testProd.getUnitsInStock() == 5);
        assertTrue("amt_on_order did not match",
                testProd.getUnitsOnOrder() == 10);
        assertTrue("threshold did not match", testProd.getReorderLevel() == 5);
        assertFalse("discontinued flag did not match",
                testProd.isDiscontinued());

        pDAO.removeProduct(testProd);
        // but not here
        testProd = pDAO.getProductByProductID(prodId);
        assertNull("expected no record to be returned (null)", testProd);
    }

    @Test
    public void test_removeProductByProductID() throws Exception {
        Product newProd = new Product(-1, "tacos", "tacos desc",
                "mexican food", 15.99, 5, 10, 5, false);

        int prodId = pDAO.createProduct(newProd);
        Product testProd = pDAO.getProductByProductID(prodId);

        // should exist here
        assertTrue("name did not match", testProd.getProductName()
                .equalsIgnoreCase("tacos"));
        assertTrue("description did not match", testProd.getDescription()
                .equalsIgnoreCase("tacos desc"));
        assertTrue("categoryName did not match", testProd.getCategoryName()
                .equalsIgnoreCase("mexican food"));
        assertTrue("price did not match", testProd.getUnitPrice() == 15.99);
        assertTrue("amt_in_stock did not match",
                testProd.getUnitsInStock() == 5);
        assertTrue("amt_on_order did not match",
                testProd.getUnitsOnOrder() == 10);
        assertTrue("threshold did not match", testProd.getReorderLevel() == 5);
        assertFalse("discontinued flag did not match",
                testProd.isDiscontinued());

        pDAO.removeProductByProductID(prodId);
        // but not here
        testProd = pDAO.getProductByProductID(prodId);
        assertNull("expected no record to be returned (null)", testProd);
    }

}

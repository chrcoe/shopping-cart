package test.dao_test;

import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.Product;

import org.apache.naming.java.javaURLContextFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import dao.ProductDAO;

public class ProductDAOTest {

    private ProductDAO pDAO;

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
        pDAO = new ProductDAO();
    }

    @After
    public void tearDown() throws Exception {
        pDAO.closeConnection();
    }

    @Test
    public void test_createProduct() throws Exception {
        Product newProd = new Product(-1, "tacos", "tacos desc", "mexican food",
                15.99, 5, 10, 5, false);

        int prodId = pDAO.createProduct(newProd);
        // the following is not yet implemented
        Product testProd = pDAO.getProductByProductID(prodId);

        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

    @Test
    public void test_getProductByProductID() throws Exception {
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

    @Test
    public void test_getProductsByCategoryName() throws Exception {
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

    @Test
    public void test_updateProduct() throws Exception {
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

    @Test
    public void test_removeProduct() throws Exception {
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

    @Test
    public void test_removeProductByProductID() throws Exception {
        // TODO: implement this test
        assertFalse("Built to fail until implemented", true);
    }

}

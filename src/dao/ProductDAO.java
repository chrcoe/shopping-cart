package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Product;

public class ProductDAO {

    private Connection con;

    public ProductDAO() throws NamingException, SQLException {

        // initialize lastAutoKeys here

        Context cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/CartDB" );

        con = ds.getConnection();

    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    // CREATE
    public int createProduct(Product newProduct) {
        // the idea here is to pass in all user info by building the object
        // first then this method will return that user's PK in the DB
        // TODO: implement this method
        int productID = -1; // need to use the lastAutoKey idea here
        String productName = null;
        String categoryName = null; // we need a category identifier .. either change
                             // this to be yet another model/table, or change to
                             // be a string value
        String description = null;
        double unitPrice = -1;
        int unitsInStock = -1;
        int unitsOnOrder = -1;
        int reorderLevel = -1;
        boolean discontinued = false;
        String imagePath = "./images/coming_soon_image.png";

        Product newProd = new Product(productID, productName, description, categoryName,
                unitPrice, unitsInStock, unitsOnOrder, reorderLevel,
                discontinued, imagePath);

        return newProd.getProductID();
    }

    // RETRIEVE
    public Product getProductByProductID(int productID) {
        // TODO: implement this method
        // would populate fields from SQL result
        String productName = null;
        String categoryName = null; // we need a category identifier .. either change
                             // this to be yet another model/table, or change to
                             // be a string value
        String description = null;
        double unitPrice = -1;
        int unitsInStock = -1;
        int unitsOnOrder = -1;
        int reorderLevel = -1;
        boolean discontinued = false;
        String imagePath = null;

        return new Product(productID, productName, description, categoryName, unitPrice,
                unitsInStock, unitsOnOrder, reorderLevel, discontinued, imagePath);
    }

    public ArrayList<Product> getProductsByCategoryName(String categoryName) {

        int productID = -1; // need to use the lastAutoKey idea here
        String productName = null;
        String description = null;
        double unitPrice = -1;
        int unitsInStock = -1;
        int unitsOnOrder = -1;
        int reorderLevel = -1;
        boolean discontinued = false;
        String imagePath = null;

        Product newProd = new Product(productID, productName, description, categoryName,
                unitPrice, unitsInStock, unitsOnOrder, reorderLevel,
                discontinued, imagePath);

        ArrayList<Product> prodList = new ArrayList<Product>();
        prodList.add(newProd); // this is just an example, would need to add the
        // entire resultset to prodList and return the entire list

        return prodList;
    }

    // UPDATE
    public void updateProduct(Product theProduct) {
        // TODO: implement this method
    }

    // DELETE
    public void removeProduct(Product theProduct) {
        // TODO: implement this method
    }

    public void removeProductByProductID(int productID) {
        // TODO: implement this method
    }
}

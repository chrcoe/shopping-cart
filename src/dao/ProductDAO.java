package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Product;

public class ProductDAO {
    private Connection con;
    private int lastProductAutoKey;

    public ProductDAO() throws NamingException, SQLException {

        // initialize lastAutoKeys here
        lastProductAutoKey = -1;

        Context cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/CartDB");

        con = ds.getConnection();
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    // CREATE
    public int createProduct(Product newProduct) throws SQLException {
        Statement s = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,
                java.sql.ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = null;

        // INSERT INTO cart_comp461_db.Product
        // (name,description,categoryName,price,amt_in_stock, amt_on_order,
        // reorder_threshold, is_discontinued)
        // VALUES (...);

        String sql = String.format(
                "INSERT INTO cart_comp461_db.Product (idProduct, name, description, "
                        + "categoryName, price, amt_in_stock, amt_on_order, "
                        + "reorder_threshold, is_discontinued) VALUES "
                        + "(null, \'%s\', \'%s\', \'%s\', %s, %s, "
                        + "%s, %s, %s)", newProduct.getProductName(),
                newProduct.getDescription(), newProduct.getCategoryName(),
                newProduct.getUnitPrice(), newProduct.getUnitsInStock(),
                newProduct.getUnitsOnOrder(), newProduct.getReorderLevel(),
                newProduct.isDiscontinued());

        s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        rs = s.getGeneratedKeys();
        rs.last();
        lastProductAutoKey = rs.getInt(1);

        s.close();
        rs.close();

        return lastProductAutoKey;
    }

    // RETRIEVE
    public Product getProductByProductID(int productID) {
        // TODO: implement this method
        // would populate fields from SQL result
        String productName = null;
        String desc = null;
        String categoryName = null; // we need a category identifier .. either
                                    // change
        // this to be yet another model/table, or change to
        // be a string value
        double unitPrice = -1;
        int unitsInStock = -1;
        int unitsOnOrder = -1;
        int reorderLevel = -1;
        boolean discontinued = false;

        return new Product(productID, productName, desc, categoryName,
                unitPrice, unitsInStock, unitsOnOrder, reorderLevel,
                discontinued);
    }

    public ArrayList<Product> getProductsByCategoryName(String categoryName) {

        int productID = -1; // need to use the lastAutoKey idea here
        String productName = null;
        String desc = null;
        double unitPrice = -1;
        int unitsInStock = -1;
        int unitsOnOrder = -1;
        int reorderLevel = -1;
        boolean discontinued = false;

        Product newProd = new Product(productID, productName, desc,
                categoryName, unitPrice, unitsInStock, unitsOnOrder,
                reorderLevel, discontinued);

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

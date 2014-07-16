package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                        // this might be the problem .. it is creating it in the DB with "null" instead of null because it it putting in \'null\' instead of null
                        // might be best to change the restrictions on the fields, or check if it's null set as empty string .. seems wrong...
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
    public Product getProductByProductID(int productID) throws SQLException {
        // SELECT * FROM cart_comp461_db.Product WHERE idProduct = <>

        Product record = null;
        String sql = "SELECT * FROM cart_comp461_db.Product "
                + "WHERE idProduct = " + productID;

        // prepared statement
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

        int id, unitsInStock, unitsOnOrder, reorderLevel;
        String productName, productDesc, categoryName;
        double unitPrice;
        boolean discontinued;

        while (rs.next()) {
            id = rs.getInt("idProduct");
            productName = rs.getString("name");
            productDesc = rs.getString("description");
            categoryName = rs.getString("categoryName");
            unitsInStock = rs.getInt("amt_in_stock");
            unitsOnOrder = rs.getInt("amt_on_order");
            reorderLevel = rs.getInt("reorder_threshold");
            unitPrice = rs.getDouble("price");
            discontinued = rs.getBoolean("is_discontinued");

            record = new Product(id, productName, productDesc, categoryName,
                    unitPrice, unitsInStock, unitsOnOrder, reorderLevel,
                    discontinued);
        }

        s.close();
        rs.close();

        return record;
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
    public void updateProduct(int productId, Product theProduct) throws SQLException {
        // UPDATE <table> SET <column>=<value> WHERE ID=<id>

        String sql = "UPDATE cart_comp461_db.Product SET "
                + "name = ?, description = ?, categoryName = ?, price = ?," +
                "amt_in_stock = ?, amt_on_order = ?, reorder_threshold = ?," +
                "is_discontinued = ? WHERE idProduct = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, theProduct.getProductName());
        // TODO: trace getDescription during update .. it sometimes passes "null" instead of null
        ps.setString(2, theProduct.getDescription());
        ps.setString(3, theProduct.getCategoryName());
        ps.setDouble(4, theProduct.getUnitPrice());
        ps.setInt(5, theProduct.getUnitsInStock());
        ps.setInt(6, theProduct.getUnitsOnOrder());
        ps.setInt(7, theProduct.getReorderLevel());
        ps.setBoolean(8, theProduct.isDiscontinued());
        ps.setInt(9, productId);

        ps.executeUpdate();

        ps.close();
    }

    // DELETE
    public void removeProduct(Product theProduct) throws SQLException {
        // DELETE FROM cart_comp461_db.Product WHERE ID = <id>

        String sql = "DELETE FROM cart_comp461_db.Product WHERE idProduct = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, theProduct.getProductID());

        ps.execute();
        ps.close();
    }

    public void removeProductByProductID(int productID) throws SQLException {
        // DELETE FROM cart_comp461_db.Product WHERE ID = <id>

        String sql = "DELETE FROM cart_comp461_db.Product WHERE idProduct = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productID);

        ps.execute();
        ps.close();
    }
}

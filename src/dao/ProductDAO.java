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

        String sql = "INSERT INTO cart_comp461_db.Product (idProduct, name, "
                + "description, categoryName, price, amt_in_stock, "
                + "amt_on_order, reorder_threshold, is_discontinued) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ResultSet rs = null;

        PreparedStatement ps = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        ps.setNull(1, java.sql.Types.INTEGER);
        ps.setString(2, newProduct.getProductName());
        ps.setString(3, newProduct.getDescription());
        ps.setString(4, newProduct.getCategoryName());
        ps.setDouble(5, newProduct.getUnitPrice());
        ps.setDouble(6, newProduct.getUnitsInStock());
        ps.setDouble(7, newProduct.getUnitsOnOrder());
        ps.setInt(8, newProduct.getReorderLevel());
        ps.setBoolean(9, newProduct.isDiscontinued());

        ps.executeUpdate();
        rs = ps.getGeneratedKeys();
        rs.last();
        lastProductAutoKey = rs.getInt(1);

        rs.close();
        ps.close();

        return lastProductAutoKey;
    }

    // RETRIEVE
    public Product getProductByProductID(int productID) throws SQLException {
        // SELECT * FROM cart_comp461_db.Product WHERE idProduct = <>

        Product record = null;
        String sql = "SELECT * FROM cart_comp461_db.Product "
                + "WHERE idProduct = " + productID;

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

        int id, unitsInStock, unitsOnOrder, reorderLevel;
        String productName, productDesc, categoryName;
        double unitPrice;
        boolean discontinued;
        String imagePath = null;

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
                    discontinued, imagePath);
        }

        s.close();
        rs.close();

        return record;
    }

    public ArrayList<Product> getProductsByCategoryName(String categoryName)
            throws SQLException {

        String sql = String.format("SELECT * FROM cart_comp461_db.Product WHERE "
                + "categoryName = \'%s\'", categoryName);

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

        ArrayList<Product> prodList = new ArrayList<Product>();
        int productID, unitsInStock, unitsOnOrder, reorderLevel;
        String productName, productDesc, catName;
        double unitPrice;
        boolean discontinued;
        String imagePath = null;

        while (rs.next()) {

            productID = rs.getInt("idProduct");
            productName = rs.getString("name");
            productDesc = rs.getString("description");
            catName = rs.getString("categoryName");
            unitPrice = rs.getDouble("price");
            unitsInStock = rs.getInt("amt_in_stock");
            unitsOnOrder = rs.getInt("amt_on_order");
            reorderLevel = rs.getInt("reorder_threshold");
            discontinued = rs.getBoolean("is_discontinued");

            Product newProd = new Product(productID, productName, productDesc,
                    catName, unitPrice, unitsInStock, unitsOnOrder,
                    reorderLevel, discontinued, imagePath);

            prodList.add(newProd);
        }

        return prodList;
    }

    // UPDATE
    public void updateProduct(int productId, Product theProduct)
            throws SQLException {
        // UPDATE <table> SET <column>=<value> WHERE ID=<id>

        String sql = "UPDATE cart_comp461_db.Product SET "
                + "name = ?, description = ?, categoryName = ?, price = ?,"
                + "amt_in_stock = ?, amt_on_order = ?, reorder_threshold = ?,"
                + "is_discontinued = ? WHERE idProduct = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, theProduct.getProductName());
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

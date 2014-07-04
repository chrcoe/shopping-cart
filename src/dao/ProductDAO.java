package dao;

import java.util.ArrayList;

import model.Product;

public class ProductDAO {

    // TODO: figure out singleton DBConnection...

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
        double unitPrice = -1;
        int unitsInStock = -1;
        int unitsOnOrder = -1;
        int reorderLevel = -1;
        boolean discontinued = false;

        Product newProd = new Product(productID, productName, categoryName,
                unitPrice, unitsInStock, unitsOnOrder, reorderLevel,
                discontinued);

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
        double unitPrice = -1;
        int unitsInStock = -1;
        int unitsOnOrder = -1;
        int reorderLevel = -1;
        boolean discontinued = false;

        return new Product(productID, productName, categoryName, unitPrice,
                unitsInStock, unitsOnOrder, reorderLevel, discontinued);
    }

    public ArrayList<Product> getProductsByCategoryName(String categoryName) {

        int productID = -1; // need to use the lastAutoKey idea here
        String productName = null;
        double unitPrice = -1;
        int unitsInStock = -1;
        int unitsOnOrder = -1;
        int reorderLevel = -1;
        boolean discontinued = false;

        Product newProd = new Product(productID, productName, categoryName,
                unitPrice, unitsInStock, unitsOnOrder, reorderLevel,
                discontinued);

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

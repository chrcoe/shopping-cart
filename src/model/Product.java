package model;

public class Product {
    int productID;
    String productName;
    String productDesc;
    String categoryName;
    double unitPrice;
    int unitsInStock;
    int unitsOnOrder;
    int reorderLevel;
    boolean discontinued;

    public Product() {

    }

    public Product(int productID, String productName, String productDesc,
            String categoryName, double unitPrice, int unitsInStock,
            int unitsOnOrder, int reorderLevel, boolean discontinued) {
        this.productID = productID;
        this.productName = productName;
        this.productDesc = productDesc;
        this.categoryName = categoryName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
    }

    public String getDescription() {
        return productDesc;
    }

    public void setDescription(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }
}

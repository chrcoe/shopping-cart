package dao;

import java.util.ArrayList;

import model.CartItem;

public class CartItemDAO {

    // TODO: figure out singleton DBConnection...

    // CREATE
    public CartItem createCartItemByCartID(int cartID) {
        // TODO: implement this method
        int cartItemID = -1; // need to use the lastAutoKey idea here
        int productID = -1;
        int quantity = -1;
        double linePrice = -1.1;
        return new CartItem(cartItemID, productID, cartID, quantity, linePrice);
    }

    // RETRIEVE
    public ArrayList<CartItem> getCartItemsByCartID(int cartID) {
        // TODO: implement this method
        int cartItemID = -1;
        int productID = -1;
        int quantity = -1;
        double linePrice = -1.1;

        // populate list from SQL resultset
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(new CartItem(cartItemID, productID, cartID, quantity,
                linePrice));

        return cartItems;
    }

    public ArrayList<CartItem> getCartItemsByProductID(int productID) {
        // TODO: implement this method
        int cartItemID = -1;
        int cartID = -1;
        int quantity = -1;
        double linePrice = -1.1;

        // populate list from SQL resultset
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(new CartItem(cartItemID, productID, cartID, quantity,
                linePrice));

        return cartItems;
    }

    // UPDATE
    public void updateCartItem(CartItem theCartItem) {
        // TODO: implement this method
    }

    // DELETE
    public void removeOrderItem(CartItem theCartItem) {
        // TODO: implement this method
    }
}

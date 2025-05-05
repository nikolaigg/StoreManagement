package customer;

import products.DeliveredProduct;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Customer {

    private ArrayList<CartItem> shoppingCart;
    private BigDecimal wallet;

    public Customer(BigDecimal wallet) {
        this.wallet = wallet;
        this.shoppingCart = new ArrayList<>();
    }

    public boolean addToShoppingCart(DeliveredProduct item, int amount) {
        return shoppingCart.add(new CartItem(item, amount));
    }
    public BigDecimal shoppingCartTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for(CartItem item : shoppingCart) {
            for(int i = 0; i < item.getAmount(); i++) {
                total = total.add(item.getGood().getSellPrice());
            }
        }
        return total;
    }
    public ArrayList<CartItem> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<CartItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "shoppingCart=" + shoppingCart +
                ", wallet=" + wallet +
                '}';
    }
}

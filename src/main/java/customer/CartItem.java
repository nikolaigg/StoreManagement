package customer;

import products.StockProduct;

import java.io.Serializable;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 11111L;
    private StockProduct product;
    private int amount;

    public CartItem(StockProduct product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public StockProduct getCartItemProduct() {
        return product;
    }

    public void setCartItemProduct(StockProduct product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", amount=" + amount +
                '}';
    }
}

package customer;

import products.DeliveredProduct;

public class CartItem {
    private DeliveredProduct good;
    private int amount;

    public CartItem(DeliveredProduct good, int amount) {
        this.good = good;
        this.amount = amount;
    }

    public DeliveredProduct getGood() {
        return good;
    }

    public void setGood(DeliveredProduct good) {
        this.good = good;
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
                "good=" + good +
                ", amount=" + amount +
                '}';
    }
}

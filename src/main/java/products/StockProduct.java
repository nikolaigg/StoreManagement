package products;

import java.io.Serial;
import java.io.Serializable;

public class StockProduct implements Serializable{
    @Serial
    private static final long serialVersionUID = 141241412L;

    private DeliveredProduct product;
    private int quantity;

    public StockProduct(DeliveredProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public DeliveredProduct getProduct() {
        return product;
    }

    public void setProduct(DeliveredProduct product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockProduct{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

}

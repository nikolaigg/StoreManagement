package products;

import java.math.BigDecimal;

public class NonFoodProduct extends DeliveredProduct {
    private static final long serialVersionUID = 8L;

    public NonFoodProduct(String name, BigDecimal deliveryPrice, int quantity) {
        super(name, deliveryPrice, quantity);
    }

    @Override
    public String toString() {
        return super.toString() + "category - non food";
    }
}

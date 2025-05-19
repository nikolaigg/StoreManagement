package products;

import java.io.Serial;
import java.math.BigDecimal;

public class NonFoodProduct extends DeliveredProduct {
    @Serial
    private static final long serialVersionUID = 8L;

    public NonFoodProduct(String name, BigDecimal deliveryPrice) {
        super(name, deliveryPrice);
    }

    @Override
    public String toString() {
        return super.toString() + "category - non food";
    }
}

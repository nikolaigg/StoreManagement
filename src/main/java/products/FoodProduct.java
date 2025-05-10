package products;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FoodProduct extends DeliveredProduct {
    private static final long serialVersionUID = 7L;

    private LocalDate expiryDate;

    public FoodProduct(String name, BigDecimal deliveryPrice, LocalDate expiryDate) {
        super(name, deliveryPrice);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }


    @Override
    public String toString() {
        return super.toString() + ", category - Food, expiryDate: " + expiryDate;
    }
}

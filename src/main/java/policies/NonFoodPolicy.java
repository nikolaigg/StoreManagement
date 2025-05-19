package policies;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class NonFoodPolicy implements PricingPolicy, Serializable {
    @Serial
    private static final long serialVersionUID = 63451L;

    private int markup;

    public NonFoodPolicy(int markup) {
        this.markup = markup;
    }

    public int getMarkup() {
        return markup;
    }

    public void setMarkup(int markup) {
        this.markup = markup;
    }


    @Override
    public BigDecimal calculatePrice(BigDecimal deliveryPrice) {
        if (deliveryPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Delivery price cannot be negative");
        }

        return deliveryPrice.multiply(BigDecimal.ONE.add(BigDecimal.valueOf(markup).divide(BigDecimal.valueOf(100))))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "NonFoodMarkupPolicy{" +
                "markup=" + markup +
                '}';
    }

}

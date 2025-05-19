package policies;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FoodPolicy implements PricingPolicy, Serializable {
    @Serial
    private static final long serialVersionUID = 57547451L;

    private int markup;
    private int expiryDiscount;
    private int daysTillExpiryDiscount;

    public FoodPolicy(int markup, int expiryDiscount, int daysTillExpiryDiscount) {
        this.markup = markup;
        this.expiryDiscount = expiryDiscount;
        this.daysTillExpiryDiscount = daysTillExpiryDiscount;
    }

    public int getMarkup() {
        return markup;
    }

    public void setMarkup(int markup) {
        this.markup = markup;
    }

    public int getExpiryDiscount() {
        return expiryDiscount;
    }

    public void setExpiryDiscount(int expiryDiscount) {
        this.expiryDiscount = expiryDiscount;
    }

    public int getDaysTillExpiryDiscount() {
        return daysTillExpiryDiscount;
    }

    public void setDaysTillExpiryDiscount(int daysTillExpiryDiscount) {
        this.daysTillExpiryDiscount = daysTillExpiryDiscount;
    }

    @Override
    public BigDecimal calculatePrice(BigDecimal deliveryPrice) {
        if (deliveryPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Delivery price cannot be negative");
        }

        return deliveryPrice.multiply(BigDecimal.ONE.add(BigDecimal.valueOf(markup).divide(BigDecimal.valueOf(100))))
                .setScale(2, RoundingMode.HALF_UP);  // Round to 2 decimal places
    }

    public BigDecimal finalPrice(BigDecimal deliveryPrice, LocalDate expiryDate) {
        BigDecimal sellPrice = calculatePrice(deliveryPrice);

        if (closeToExpiryDiscount(expiryDate)) {
            BigDecimal discountMultiplier = BigDecimal.ONE.subtract(BigDecimal.valueOf(expiryDiscount).divide(BigDecimal.valueOf(100)));
            sellPrice = sellPrice.multiply(discountMultiplier);
        }
        return sellPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean closeToExpiryDiscount(LocalDate expiryDate) {
        LocalDate today = LocalDate.now(); // sample date
        int daysTillExpiry = (int) ChronoUnit.DAYS.between(today,expiryDate);

        return daysTillExpiry <= daysTillExpiryDiscount;
    }

    @Override
    public String toString() {
        return "FoodMarkupPolicy{" +
                "markup=" + markup +
                ", expiryDiscount=" + expiryDiscount +
                ", daysTillExpiryDiscount=" + daysTillExpiryDiscount +
                '}';
    }
}

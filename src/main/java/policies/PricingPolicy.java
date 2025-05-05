package policies;

import java.math.BigDecimal;

public interface PricingPolicy {
    BigDecimal calculatePrice(BigDecimal deliveryPrice);
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import policies.NonFoodPolicy;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NonFoodPolicyTest {

    private NonFoodPolicy policy;
    @BeforeEach
    void setUp() {
        policy = new NonFoodPolicy(10);
    }
    @Test
    public void testCalculatePrice_WithNegativeDeliveryPrice() {
        BigDecimal negativeDeliveryPrice = new BigDecimal("-100.00");

        assertThrows(IllegalArgumentException.class, () -> {
            policy.calculatePrice(negativeDeliveryPrice);
        });
    }

    @Test
    public void testCalculatePrice_WithZeroMarkupPrice() {
        NonFoodPolicy policy = new NonFoodPolicy(0);
        BigDecimal deliveryPrice = new BigDecimal("100.00");

        BigDecimal actual = policy.calculatePrice(deliveryPrice);
        BigDecimal expected = new BigDecimal("100.00");

        assertEquals(expected, actual);
    }
    @Test
    public void testCalculatePrice_with10PercentMarkup() {
        BigDecimal deliveryPrice = new BigDecimal("100.00");

        BigDecimal actual = policy.calculatePrice(deliveryPrice);

        BigDecimal expected = new BigDecimal("110.00");

        assertEquals(expected, actual);
    }
}

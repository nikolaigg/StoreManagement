import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import policies.FoodPolicy;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodPolicyTest {

    private FoodPolicy policy;
    @BeforeEach
    void setUp() {
        policy = new FoodPolicy(10,20,5);
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
        FoodPolicy policy = new FoodPolicy(0,20,5);
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
    @Test
    public void closeToExpiryDiscount_TrueReturned() {
        LocalDate expiryDate = LocalDate.now();

        boolean actual = policy.closeToExpiryDiscount(expiryDate);
        assertTrue(actual);
    }

    @Test
    public void closeToExpiryDiscount_FalseReturned() {
        LocalDate expiryDate = LocalDate.now().plusYears(1);

        boolean actual = policy.closeToExpiryDiscount(expiryDate);
        assertFalse(actual);
    }

    @Test void testFinalPriceWithoutExpiryDiscount() {
        LocalDate expiryDate = LocalDate.now().plusYears(1);
        BigDecimal deliveryPrice = new BigDecimal("100.00");

        BigDecimal actual = policy.finalPrice(deliveryPrice, expiryDate);
        BigDecimal expected = new BigDecimal("110.00");

        assertEquals(expected, actual);
    }

    @Test void testFinalPriceWithExpiryDiscount() {
        LocalDate expiryDate = LocalDate.now();
        BigDecimal deliveryPrice = new BigDecimal("100.00");

        BigDecimal actual = policy.finalPrice(deliveryPrice, expiryDate);
        BigDecimal expected = new BigDecimal("88.00");

        assertEquals(expected, actual);
    }
}

import customer.Customer;
import exceptions.NoCashierOnPaydeskException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import store.staff.Cashier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CashierTest {

    @Test
    public void testSellingProductsOnEmptyPaydesk_ThrowsException() {
        Cashier cashier = new Cashier("Test", BigDecimal.valueOf(1000));

        assertThrows(NoCashierOnPaydeskException.class, () -> {
            cashier.sellProducts(Mockito.mock(Customer.class));
        });
    }
}

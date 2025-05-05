import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import store.receipt.Receipt;
import store.transaction.Paydesk;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaydeskTest {

    @Test
    public void testStoringReceipts_ReturnsNotEmpty() {
        Paydesk p = new Paydesk();
        Receipt receipt = Mockito.mock(Receipt.class);
        p.storeReceipt(receipt);

        assertFalse(p.getStoredReceipts().isEmpty());
    }
}

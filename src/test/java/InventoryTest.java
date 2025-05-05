import customer.CartItem;
import exceptions.UnavailableStockRuntimeException;
import exceptions.UnavailableStockException;
import products.DeliveredProduct;
import products.NonFoodProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import store.inventory.Inventory;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// DOVURSHI
public class InventoryTest {

    private Inventory inventory;
    private DeliveredProduct product;
    private DeliveredProduct product2;

    @BeforeEach
    public void setUp() {
        product = Mockito.mock(DeliveredProduct.class);
        product2 = new NonFoodProduct("Test", BigDecimal.valueOf(100), 10);
        ArrayList<DeliveredProduct> deliveredProductList = new ArrayList<>();
        deliveredProductList.add(product);
        deliveredProductList.add(product2);

        inventory = new Inventory(deliveredProductList);
    }

    @Test
    public void testStockingUpWithNegativeAmount() {
        int negativeAmount = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            inventory.stockUp(product, negativeAmount);
        });

    }

    // amount ne e nai dobrata logika
    @Test
    public void testStockingUpProductNotInInventory(){
        DeliveredProduct productNotInInventory = new NonFoodProduct("Test", BigDecimal.valueOf(100), 5);

        inventory.stockUp(productNotInInventory, 10);

        assertTrue(inventory.getGoods().contains(productNotInInventory));
        assertEquals(10, productNotInInventory.getQuantity());
    }

    @Test
    public void testStockingUpWithPositiveAmount() {
        inventory.stockUp(product2, 5);

        int expected = 15;

        assertEquals(expected, product2.getQuantity());

    }

    @Test
    public void testReducingStockOfProductNotInInventory() {
        DeliveredProduct productNotFound = Mockito.mock(DeliveredProduct.class);

        assertThrows(UnavailableStockRuntimeException.class, () -> {
            inventory.reduceStock(productNotFound, 1);
        });
    }
    @Test
    public void testReducingStockWithNegativeAmount() {
        int negativeAmount = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            inventory.reduceStock(product, negativeAmount);
        });
    }

    @Test
    public void testReducingStockWithPositiveAmount() {
        inventory.reduceStock(product2, 5);

        int expected = 5;

        assertEquals(expected, product2.getQuantity());
    }

    // TO DO - FIX
    @Test
    public void checkForUnavailableItems_ThrowsException(){
        CartItem testItem = new CartItem(new NonFoodProduct("Test2", BigDecimal.valueOf(100), 10), 5);
        ArrayList<CartItem> items = new ArrayList<>();
        items.add(testItem);

        assertThrows(UnavailableStockException.class, () -> {
            inventory.checkForUnavailableItems(items);
        });
    }
}

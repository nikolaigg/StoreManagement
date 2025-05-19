import exceptions.UnavailableStockRuntimeException;
import products.FoodProduct;
import products.NonFoodProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import products.StockProduct;
import store.inventory.Inventory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;
    private StockProduct product1;
    private StockProduct product2;

    @BeforeEach
    public void setUp() {
        product1 = Mockito.mock(StockProduct.class);
        product2 = new StockProduct(new NonFoodProduct("Test", BigDecimal.valueOf(100)), 10);
        ArrayList<StockProduct> stockProductsList = new ArrayList<>();
        stockProductsList.add(product1);
        stockProductsList.add(product2);

        inventory = new Inventory(stockProductsList);
    }

    @Test
    public void testStockingUpWithNegativeAmount() {
        int negativeAmount = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            inventory.stockUp(product1, negativeAmount);
        });

    }

    @Test
    public void testStockingUpProductNotInInventory(){
        StockProduct productNotInInventory = new StockProduct(new NonFoodProduct("Test", BigDecimal.valueOf(100)),5);

        inventory.stockUp(productNotInInventory, 10);

        assertTrue(inventory.getStockProducts().contains(productNotInInventory));
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
        StockProduct productNotFound = Mockito.mock(StockProduct.class);

        assertThrows(UnavailableStockRuntimeException.class, () -> {
            inventory.reduceStock(productNotFound, 1);
        });
    }
    @Test
    public void testReducingStockWithNegativeAmount() {
        int negativeAmount = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            inventory.reduceStock(product1, negativeAmount);
        });
    }

    @Test
    public void testReducingStockWithPositiveAmount() {
        inventory.reduceStock(product2, 5);

        int expected = 5;

        assertEquals(expected, product2.getQuantity());
    }

    @Test
    public void testRemovingExpiredProductFromInventory_ReturnsTrue() {
        StockProduct expiredProduct = new StockProduct(new FoodProduct("Test", BigDecimal.valueOf(100), LocalDate.of(2024,1,1)), 10);
        inventory.stockUp(expiredProduct, 5);

        inventory.removeExpiredStock();

        assertTrue(!inventory.getStockProducts().contains(expiredProduct));
    }

}

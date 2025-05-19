package loaders;

import products.FoodProduct;
import products.NonFoodProduct;
import products.StockProduct;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductsLoader {

    public static ArrayList<StockProduct> loadProducts() {

        FoodProduct breadUnprocessed = new FoodProduct("BurgasHlqb", BigDecimal.valueOf(0.6), LocalDate.of(2026,1,1));
        FoodProduct milkUnprocessed = new FoodProduct("Vereq", BigDecimal.valueOf(1.2),LocalDate.of(2026,1,1));
        FoodProduct cheeseUnprocessed = new FoodProduct("Sirene Saqna", BigDecimal.valueOf(4), LocalDate.of(2026,1,1));
        FoodProduct waterUnprocessed = new FoodProduct("Devin", BigDecimal.valueOf(0.2), LocalDate.of(2026,1,1));
        FoodProduct eggsUnprocessed = new FoodProduct("Akvilon", BigDecimal.valueOf(1.5), LocalDate.of(2026,1,1));
        FoodProduct chickenBreastsUnprocessed = new FoodProduct("Pileshki gurdi", BigDecimal.valueOf(5), LocalDate.of(2026,1,1));
        FoodProduct porkUnprocessed = new FoodProduct("Svinski vrat", BigDecimal.valueOf(5), LocalDate.of(2026,1,1));
        FoodProduct frozenPizzaUnprocessed = new FoodProduct("Pizza Felicana", BigDecimal.valueOf(3), LocalDate.of(2026,1,1));
        FoodProduct tomatoesUnprocessed = new FoodProduct("Rozovi domati", BigDecimal.valueOf(1.4), LocalDate.of(2025,6,23));
        FoodProduct cucumbersUnprocessed = new FoodProduct("Krastavici", BigDecimal.valueOf(1.4), LocalDate.of(2026,6,23));

        NonFoodProduct laptopUnprocessed = new NonFoodProduct("MSI", BigDecimal.valueOf(700));
        NonFoodProduct tvUnprocessed = new NonFoodProduct("LG", BigDecimal.valueOf(250));
        NonFoodProduct smartphoneUnprocessed = new NonFoodProduct("Samsung", BigDecimal.valueOf(1100));
        NonFoodProduct tabletUnprocessed = new NonFoodProduct("Apple Ipad", BigDecimal.valueOf(400));
        NonFoodProduct toiletPaperUnprocessed = new NonFoodProduct("Emeka", BigDecimal.valueOf(2));
        NonFoodProduct detergentUnprocessed = new NonFoodProduct("Ariel", BigDecimal.valueOf(10));
        NonFoodProduct toothpasteUnprocessed = new NonFoodProduct("Lacalut", BigDecimal.valueOf(0.8));
        NonFoodProduct toothbrushUnprocessed = new NonFoodProduct("Colgate", BigDecimal.valueOf(0.4));
        NonFoodProduct electricToothbrushUnprocessed = new NonFoodProduct("Oral-B", BigDecimal.valueOf(20));
        NonFoodProduct batteriesPackUnprocessed = new NonFoodProduct("Duracell", BigDecimal.valueOf(2.6));

        StockProduct breadStock = new StockProduct(breadUnprocessed, 20);
        StockProduct milkStock = new StockProduct(milkUnprocessed, 20);
        StockProduct cheeseStock = new StockProduct(cheeseUnprocessed, 10);
        StockProduct waterStock = new StockProduct(waterUnprocessed, 20);
        StockProduct eggsStock = new StockProduct(eggsUnprocessed, 20);
        StockProduct chickenBreastsStock = new StockProduct(chickenBreastsUnprocessed, 10);
        StockProduct porkStock = new StockProduct(porkUnprocessed, 10);
        StockProduct frozenPizzaStock = new StockProduct(frozenPizzaUnprocessed, 10);
        StockProduct tomatoesStock = new StockProduct(tomatoesUnprocessed, 20);
        StockProduct cucumbersStock = new StockProduct(cucumbersUnprocessed, 20);

        StockProduct laptopStock = new StockProduct(laptopUnprocessed, 5);
        StockProduct tvStock = new StockProduct(tvUnprocessed, 5);
        StockProduct smartphoneStock = new StockProduct(smartphoneUnprocessed, 5);
        StockProduct tabletStock = new StockProduct(tabletUnprocessed, 5);
        StockProduct toiletPaperStock = new StockProduct(toiletPaperUnprocessed, 15);
        StockProduct detergentStock = new StockProduct(detergentUnprocessed, 10);
        StockProduct toothpasteStock = new StockProduct(toothpasteUnprocessed, 10);
        StockProduct toothbrushStock = new StockProduct(toothbrushUnprocessed, 10);
        StockProduct electricToothbrushStock = new StockProduct(electricToothbrushUnprocessed, 5);
        StockProduct batteriesPackStock = new StockProduct(batteriesPackUnprocessed, 10);

        return addProducts(breadStock,milkStock,cheeseStock,waterStock,eggsStock,chickenBreastsStock,porkStock,frozenPizzaStock,tomatoesStock,cucumbersStock,
                laptopStock,tvStock,smartphoneStock,tabletStock,toiletPaperStock,detergentStock,toothpasteStock,toothbrushStock,electricToothbrushStock,batteriesPackStock);
    }

    private static ArrayList<StockProduct> addProducts(StockProduct... products) {
        return new ArrayList<>(Arrays.asList(products));
    }
}

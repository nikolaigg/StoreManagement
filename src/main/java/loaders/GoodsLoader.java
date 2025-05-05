package loaders;

import products.FoodProduct;
import products.DeliveredProduct;
import products.NonFoodProduct;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class GoodsLoader {

    public static ArrayList<DeliveredProduct> loadGoods() {

        FoodProduct bread = new FoodProduct("BurgasHlqb", BigDecimal.valueOf(0.6),20, LocalDate.of(2026,1,1));
        FoodProduct milk = new FoodProduct("Vereq", BigDecimal.valueOf(1.2),20, LocalDate.of(2026,1,1));
        FoodProduct cheese = new FoodProduct("Sirene Saqna", BigDecimal.valueOf(4),10, LocalDate.of(2026,1,1));
        FoodProduct water = new FoodProduct("Devin", BigDecimal.valueOf(0.2),20, LocalDate.of(2026,1,1));
        FoodProduct eggs = new FoodProduct("Akvilon", BigDecimal.valueOf(1.5),20, LocalDate.of(2026,1,1));
        FoodProduct chickenBreasts = new FoodProduct("Pileshki gurdi", BigDecimal.valueOf(5),10, LocalDate.of(2026,1,1));
        FoodProduct pork = new FoodProduct("Svinski vrat", BigDecimal.valueOf(5),10, LocalDate.of(2026,1,1));
        FoodProduct frozenPizza = new FoodProduct("Pizza Felicana", BigDecimal.valueOf(3),10, LocalDate.of(2026,1,1));
        FoodProduct tomatoes = new FoodProduct("Rozovi domati", BigDecimal.valueOf(1.4),20, LocalDate.of(2025,6,23));
        FoodProduct cucumbers = new FoodProduct("Krastavici", BigDecimal.valueOf(1.4),20, LocalDate.of(2026,6,23));

        NonFoodProduct laptop = new NonFoodProduct("MSI", BigDecimal.valueOf(700),5);
        NonFoodProduct tv = new NonFoodProduct("LG", BigDecimal.valueOf(250),5);
        NonFoodProduct smartphone = new NonFoodProduct("Samsung", BigDecimal.valueOf(1100),5);
        NonFoodProduct tablet = new NonFoodProduct("Apple Ipad", BigDecimal.valueOf(400),5);
        NonFoodProduct toiletPaper = new NonFoodProduct("Emeka", BigDecimal.valueOf(2),15);
        NonFoodProduct detergent = new NonFoodProduct("Ariel", BigDecimal.valueOf(10),10);
        NonFoodProduct toothpaste = new NonFoodProduct("Lacalut", BigDecimal.valueOf(0.8),10);
        NonFoodProduct toothbrush = new NonFoodProduct("Colgate", BigDecimal.valueOf(0.4),10);
        NonFoodProduct electricToothbrush = new NonFoodProduct("Oral-B", BigDecimal.valueOf(20),5);
        NonFoodProduct batteriesPack = new NonFoodProduct("Duracell", BigDecimal.valueOf(2.6),10);


        return addGoods(bread,milk,cheese,water,eggs,chickenBreasts,pork,frozenPizza,tomatoes,cucumbers,
                        laptop,tv,smartphone,tablet,toiletPaper,detergent,toothpaste,toothbrush,electricToothbrush,batteriesPack);
    }

    private static ArrayList<DeliveredProduct> addGoods(DeliveredProduct... goods) {
        ArrayList<DeliveredProduct> deliveredProductList = new ArrayList<>();
        Collections.addAll(deliveredProductList, goods);

        return deliveredProductList;
    }
}

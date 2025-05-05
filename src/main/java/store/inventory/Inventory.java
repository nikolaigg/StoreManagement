package store.inventory;

import customer.CartItem;
import exceptions.InsufficientStockException;
import exceptions.InvalidStockRequestException;
import exceptions.UnavailableStockRuntimeException;
import exceptions.UnavailableStockException;
import products.FoodProduct;
import products.DeliveredProduct;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory implements Serializable {
    private static final long serialVersionUID = 5L;

    private ArrayList<DeliveredProduct> goods;

    public Inventory(ArrayList<DeliveredProduct> goods) {
        this.goods = goods;
    }
    public BigDecimal totalGoodsDeliveryExpenses(){
        BigDecimal total = BigDecimal.ZERO;
        for (DeliveredProduct good : goods) {
            total = total.add(good.getDeliveryPrice());
        }
        return total;

    }

    public void printInventory() {
        System.out.println("================= STORE INVENTORY =================");
        System.out.printf("%-5s| %-18s| %-9s| %-8s| %-5s| %-12s\n",
                "ID", "Name", "Category", "Price", "Qty", "Expiry Date");
        System.out.println("---------------------------------------------------------------");

        for (DeliveredProduct good: goods) {
            String foodCategory = good instanceof FoodProduct ? "Food" : "Non-Food";
            String expiryDate = good instanceof FoodProduct ? ((FoodProduct) good).getExpiryDate().toString() : "N/A";
            System.out.printf("%-5d| %-18s| %-9s| $%-7.2f| %-5d| %-12s\n",
                    good.getId(), good.getName(), foodCategory, good.getSellPrice(), good.getQuantity(), expiryDate);
        }

        System.out.println("===============================================================");
    }

    public void checkForUnavailableItems(ArrayList<CartItem> shoppingCart) {
        Iterator<CartItem> iterator = shoppingCart.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            try {
                if (!this.getGoods().contains(item.getGood())) {
                    throw new UnavailableStockException("Item " + item.getGood() + " is not available in our store");
                }
            } catch (UnavailableStockException e) {
                System.out.println(e.getMessage());
                System.out.println("Removing item from shopping cart");
                iterator.remove();
            }
        }
    }

    public void checkItemAvailability(ArrayList<CartItem> shoppingCart) {
        Iterator<CartItem> iterator = shoppingCart.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            try{
                if(item.getAmount() > item.getGood().getQuantity()){
                    throw new InsufficientStockException("We only have " + item.getGood().getQuantity() + " of "+ item.getGood().getName() + " in stock" +
                            "\n" + (item.getAmount() -item.getGood().getQuantity() ) + " less than desired");
                }
            } catch (InsufficientStockException e) {
                System.out.println(e.getMessage());
                System.out.println("Selling you what we have left...");
                item.setAmount(item.getGood().getQuantity());
            }
        }
    }

    public void reduceStock(DeliveredProduct good, int amount) {
        if(!goods.contains(good)) {
            throw new UnavailableStockRuntimeException("No such stock in store");
        }
        if(amount <= 0)
        {
            throw new IllegalArgumentException("Products cannot have negative quantity");
        }

        if(amount > good.getQuantity()) {
            throw new InvalidStockRequestException("Cannot reduce stock by amount more than available");
        }

        good.setQuantity(good.getQuantity() - amount);
    }

    public void stockUp(DeliveredProduct good, int amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Products cannot have negative quantity");
        }

        if(!goods.contains(good)) {
            good.setQuantity(amount);
            goods.add(good);
        }
        else{
            good.setQuantity(good.getQuantity() + amount);
        }

    }

    public ArrayList<DeliveredProduct> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<DeliveredProduct> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "goods=" + goods +
                '}';
    }
}

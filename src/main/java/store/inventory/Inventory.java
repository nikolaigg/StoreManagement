package store.inventory;

import customer.CartItem;
import exceptions.InsufficientStockException;
import exceptions.InvalidStockRequestException;
import exceptions.UnavailableStockRuntimeException;
import exceptions.UnavailableStockException;
import products.FoodProduct;
import products.StockProduct;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory implements Serializable {
    private static final long serialVersionUID = 5L;

    private ArrayList<StockProduct> products;

    public Inventory(ArrayList<StockProduct> products) {
        this.products = products;
    }
    public BigDecimal totalGoodsDeliveryExpenses(){
        BigDecimal total = BigDecimal.ZERO;
        for (StockProduct product : products) {
            total = total.add(product.getProduct().getDeliveryPrice());
        }
        return total;

    }

    public void printInventory(String storeName) {
        System.out.println("\n================= " + storeName + "'s Inventory =================");
        System.out.printf("%-5s| %-18s| %-9s| %-8s| %-5s| %-12s\n",
                "ID", "Name", "Category", "Price", "Qty", "Expiry Date");
        System.out.println("---------------------------------------------------------------");

        for (StockProduct product: products) {
            String foodCategory = product.getProduct() instanceof FoodProduct ? "Food" : "Non-Food";
            String expiryDate = product.getProduct() instanceof FoodProduct ? ((FoodProduct) product.getProduct()).getExpiryDate().toString() : "N/A";
            System.out.printf("%-5d| %-18s| %-9s| $%-7.2f| %-5d| %-12s\n",
                    product.getProduct().getId(), product.getProduct().getName(), foodCategory, product.getProduct().getSellPrice(), product.getQuantity(), expiryDate);
        }

        System.out.println("===============================================================\n");
    }

    public void checkForUnavailableItems(ArrayList<CartItem> shoppingCart) {
        Iterator<CartItem> iterator = shoppingCart.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            try {
                if (!this.getStockProducts().contains(item.getCartItemProduct())) {
                    throw new UnavailableStockException("Item " + item.getCartItemProduct() + " is not available in our store");
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
                if(item.getAmount() > item.getCartItemProduct().getQuantity()){
                    throw new InsufficientStockException("We only have " + item.getCartItemProduct().getQuantity() + " of "+ item.getCartItemProduct().getProduct().getName() + " in stock" +
                            "\n" + (item.getAmount() -item.getCartItemProduct().getQuantity() ) + " less than desired");
                }
            } catch (InsufficientStockException e) {
                System.out.println(e.getMessage());
                System.out.println("Selling you what we have left...");
                item.setAmount(item.getCartItemProduct().getQuantity());
            }
        }
    }

    public void reduceStock(StockProduct product, int amount) {
        if(!products.contains(product)) {
            throw new UnavailableStockRuntimeException("No such stock in store");
        }
        if(amount <= 0)
        {
            throw new IllegalArgumentException("Products cannot have negative quantity");
        }

        if(amount > product.getQuantity()) {
            throw new InvalidStockRequestException("Cannot reduce stock by amount more than available");
        }

        product.setQuantity(product.getQuantity() - amount);
    }

    public void stockUp(StockProduct product, int amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Products cannot have negative quantity");
        }

        if(!products.contains(product)) {
            product.setQuantity(amount);
            products.add(product);
        }
        else{
            product.setQuantity(product.getQuantity() + amount);
        }

    }

    public boolean removeExpiredStock() {
        LocalDate today = LocalDate.now();
        Iterator<StockProduct> iterator = products.iterator();
        while (iterator.hasNext()) {
            StockProduct product = iterator.next();
            if (product.getProduct() instanceof FoodProduct food && !food.getExpiryDate().isAfter(today)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public StockProduct getStockProductById(int id) {
        for(StockProduct product : products) {
            if(product.getProduct().getId() == id) {
                return product;
            }
        }
        return null;
    }
    public ArrayList<StockProduct> getStockProducts() {
        return products;
    }

    public void setStockProducts(ArrayList<StockProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "goods=" + products +
                '}';
    }
}

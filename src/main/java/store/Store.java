package store;

import products.FoodProduct;
import products.DeliveredProduct;
import policies.FoodPolicy;
import policies.NonFoodPolicy;
import products.StockProduct;
import store.inventory.Inventory;
import store.staff.Cashier;
import store.staff.StaffManagement;
import store.transaction.Paydesk;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Store implements Serializable {
    private static final long serialVersionUID = 4L;

    private String storeName;
    private Inventory inventory;
    private StaffManagement staffManagement;
    private ArrayList<Paydesk> paydesks;

    // to do - solid DIP
    private FoodPolicy foodMarkupPolicy;
    private NonFoodPolicy nonFoodMarkupPolicy;


    public Store(String storeName, Inventory inventory, StaffManagement staffManagement,
                 FoodPolicy foodMarkupPolicy, NonFoodPolicy nonFoodMarkupPolicy, ArrayList<Paydesk> paydesks) {
        this.storeName = storeName;
        this.inventory = inventory;
        this.staffManagement = staffManagement;
        this.foodMarkupPolicy = foodMarkupPolicy;
        this.nonFoodMarkupPolicy = nonFoodMarkupPolicy;
        this.paydesks = paydesks;


        // remove expired products
        inventory.removeExpiredStock();

        // calculate sell prices for goods
        calculateGoodsPrices();
    }

    public void calculateGoodsPrices(){
        for(StockProduct product : inventory.getStockProducts()){
            if(product.getProduct() instanceof FoodProduct){
                product.getProduct() .setSellPrice(foodMarkupPolicy.finalPrice(product.getProduct() .getDeliveryPrice(), ((FoodProduct) product.getProduct() ).getExpiryDate()));
            }
            else{
                product.getProduct() .setSellPrice(nonFoodMarkupPolicy.calculatePrice(product.getProduct() .getDeliveryPrice()));
            }
        }
    }


    public BigDecimal totalPaydeskRevenue() {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        for(Paydesk p : paydesks){
            totalRevenue = totalRevenue.add(p.getTotalAmount());
        }
        return totalRevenue;
    }

    public void totalStoreExpensesAndProfit() {
        BigDecimal totalExpenses= staffManagement.totalStaffExpenses().add(inventory.totalGoodsDeliveryExpenses());
        BigDecimal totalRevenue = totalPaydeskRevenue();

        System.out.println("\n> " + storeName + " Total Expenses:");
        System.out.printf("  - Staff Salaries:          $%,10.2f\n", staffManagement.totalStaffExpenses());
        System.out.printf("  - Inventory Value:         $%,10.2f\n", inventory.totalGoodsDeliveryExpenses());
        System.out.println("  -------------------------------");
        System.out.printf("  Total Expenses:            $%,10.2f\n\n", totalExpenses);

        System.out.printf("> Total Revenue from PayDesks: $%,10.2f\n\n", totalRevenue);

        System.out.println("-----------------------------------");
        if(totalExpenses.compareTo(totalRevenue) > 0){
            System.out.printf("Loss:                   $%,10.2f\n", totalExpenses.subtract(totalRevenue));
        }
        else if(totalExpenses.compareTo(totalRevenue) < 0){
            System.out.printf("Net Profit:                   $%,10.2f\n", totalExpenses.add(totalRevenue));
        }
        else {
            System.out.printf("Store breaks even                   $%,10.2f\n", BigDecimal.ZERO);
        }
        System.out.println("===================================");

    }
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public StaffManagement getStaffManagement() {
        return staffManagement;
    }

    public void hireCashier(Cashier cashier){
        if(staffManagement.hireStaff(cashier)){
            cashier.setStore(this);
        }
    }

    public void fireCashier(Cashier cashier){
        if(staffManagement.fireStaff(cashier)){
            cashier.setStore(null);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", inventory=" + inventory +
                ", staffManagement=" + staffManagement +
                ", paydesks=" + paydesks +
                ", foodMarkupPolicy=" + foodMarkupPolicy +
                ", nonFoodMarkupPolicy=" + nonFoodMarkupPolicy +
                '}';
    }
}

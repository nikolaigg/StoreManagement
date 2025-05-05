package org.example;

import customer.Customer;
import products.DeliveredProduct;
import loaders.GoodsLoader;
import policies.FoodPolicy;
import policies.NonFoodPolicy;
import store.*;
import store.inventory.Inventory;
import store.receipt.ReceiptSerializationDeserialization;
import store.staff.Cashier;
import store.staff.StaffManagement;
import store.transaction.Paydesk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // goods
       ArrayList<DeliveredProduct> goods = GoodsLoader.loadGoods();

       // store
       Inventory inventory = new Inventory(goods);

       Paydesk paydesk1 = new Paydesk();
       Paydesk paydesk2 = new Paydesk();
       Paydesk paydesk3 = new Paydesk();
       Paydesk paydesk4 = new Paydesk();
       ArrayList<Paydesk> paydesks = new ArrayList<>(List.of(paydesk1, paydesk2, paydesk3, paydesk4));

       Cashier cashier1 = new Cashier("Ivan", BigDecimal.valueOf(1500));
       Cashier cashier2 = new Cashier("Georgi", BigDecimal.valueOf(1500));
       Cashier cashier3 = new Cashier("Emiliq", BigDecimal.valueOf(1500));

       Store store = new Store(
               "Billa",
               inventory,
               new StaffManagement(),
               new FoodPolicy(20,20,10),
               new NonFoodPolicy(40),
               paydesks
       );

       store.hireCashier(cashier1);
       store.hireCashier(cashier2);
       store.hireCashier(cashier3);
       store.getStaffManagement().assignPaydeskToStaff(cashier1,paydesks);
       store.getInventory().printInventory();

       Customer c1 = new Customer(BigDecimal.valueOf(1500));
       c1.addToShoppingCart(goods.get(0),32);
       c1.addToShoppingCart(goods.get(1),2);

       cashier1.sellProducts(c1);
       store.getInventory().printInventory();

       store.totalStoreExpensesAndProfit();

       ReceiptSerializationDeserialization receiptService = new ReceiptSerializationDeserialization();


    }

}
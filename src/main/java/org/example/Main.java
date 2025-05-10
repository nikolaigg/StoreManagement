package org.example;

import customer.Customer;
import loaders.ProductsLoader;
import policies.FoodPolicy;
import policies.NonFoodPolicy;
import products.StockProduct;
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

        // load products
       ArrayList<StockProduct> products = ProductsLoader.loadProducts();

       // init inventory
       Inventory inventory = new Inventory(products);

       // init paydesks
       Paydesk paydesk1 = new Paydesk();
       Paydesk paydesk2 = new Paydesk();
       Paydesk paydesk3 = new Paydesk();
       Paydesk paydesk4 = new Paydesk();
       ArrayList<Paydesk> paydesks = new ArrayList<>(List.of(paydesk1, paydesk2, paydesk3, paydesk4));

       // init cashiers
       Cashier cashier1 = new Cashier("Ivan", BigDecimal.valueOf(1500));
       Cashier cashier2 = new Cashier("Georgi", BigDecimal.valueOf(1500));
       Cashier cashier3 = new Cashier("Emiliq", BigDecimal.valueOf(1500));

       // init store
       Store store = new Store(
               "Billa",
               inventory,
               new StaffManagement(),
               new FoodPolicy(20,20,10),
               new NonFoodPolicy(40),
               paydesks
       );

       // hire cashiers
       store.hireCashier(cashier1);
       store.hireCashier(cashier2);
       store.hireCashier(cashier3);

       // assign cashiers to paydesks
       store.getStaffManagement().assignPaydeskToStaff(cashier1,paydesks);
       store.getStaffManagement().assignPaydeskToStaff(cashier2,paydesks);

       // init customers
       Customer c1 = new Customer(BigDecimal.valueOf(1500));

       // add to customer shopping cart
       c1.addToShoppingCart(store.getInventory().getStockProductById(1), 10);
       c1.addToShoppingCart(store.getInventory().getStockProductById(2), 22);

       System.out.println("Store inventory before selling:");
       store.getInventory().printInventory(store.getStoreName());

       // Selling products
       cashier1.sellProducts(c1);

       System.out.println("\nStore inventory after selling:");
       store.getInventory().printInventory(store.getStoreName());


       store.totalStoreExpensesAndProfit();


       // Serialization && Deserialization
       String serReceiptFilePath = "D:\\JAVA\\JavaDirectory\\Store\\src\\main\\ser\\receipt.ser";
       ReceiptSerializationDeserialization receiptService = new ReceiptSerializationDeserialization();

       receiptService.serialize(serReceiptFilePath,paydesk1.getStoredReceipts().get(0));

       System.out.println("Deserialization of receipt:");
       System.out.println(receiptService.deserialize(serReceiptFilePath));


       // ReceiptFileHandler.readAllTextFilesInFolder("D:\\JAVA\\JavaDirectory\\Store\\src\\main\\receipts");
    }

}


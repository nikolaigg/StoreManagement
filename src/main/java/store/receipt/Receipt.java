package store.receipt;

import customer.CartItem;
import store.Store;
import store.staff.Cashier;

import java.io.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Receipt implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;

    private int id;
    private Store store;
    private Cashier cashierIssuedBy;
    private String issuedOn;
    private ArrayList<CartItem> boughtItems;
    private BigDecimal totalPrice;

    public Receipt( Store store, Cashier cashierIssuedBy, String issuedOn, ArrayList<CartItem> boughtItems, BigDecimal totalPrice) {
        this.id = idCounter++;
        this.store = store;
        this.cashierIssuedBy = cashierIssuedBy;
        this.issuedOn = issuedOn;
        this.boughtItems = boughtItems;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write("--------------------------------------------\n");
            writer.write("                RECEIPT\n");
            writer.write("--------------------------------------------\n");
            writer.write("Receipt ID: " + id + "\n");
            writer.write("Store: " + store.getStoreName()+ "\n");
            writer.write("Issued by: " + cashierIssuedBy.getName() + " #" + cashierIssuedBy.getId() + "\n");
            writer.write(issuedOn+ "\n");
            writer.write("\n");
            writer.write("--------------------------------------------\n");
            writer.write("Items Purchased:\n");
            writer.write("--------------------------------------------\n");
            writer.write("ID     | Item Name          | Price   | Quantity | Total\n");
            writer.write("------------------------------------------------------\n");

            for (CartItem item : boughtItems) {
                writer.write(String.format("%-6s| %-18s| %-8.2f| %-9d| %.2f\n",
                        item.getGood().getId(),
                        item.getGood().getName(),
                        item.getGood().getSellPrice(),
                        item.getAmount(),
                        item.getGood().getSellPrice().multiply(BigDecimal.valueOf(item.getAmount()))));
            }

            writer.write("--------------------------------------------\n");
            writer.write("TOTAL:                                " + String.format("%.2f", totalPrice) + "\n");
            writer.write("--------------------------------------------\n");
            writer.write("\nThank you for shopping with us!\n");
            writer.write("--------------------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error writing receipt to file: " + e.getMessage());
        }
    }

    public static void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading receipt from file: " + e.getMessage());
        }
    }


}


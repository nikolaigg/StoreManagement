package store.transaction;

import customer.Customer;
import store.inventory.Inventory;
import store.receipt.Receipt;
import store.staff.Cashier;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Paydesk implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private static int idCounter = 1;

    private int id;
    private Cashier assignedCashier;
    private boolean isAvailable;
    private ArrayList<Receipt> storedReceipts;
    private BigDecimal totalAmount;
    private TransactionService transactionService;

    public Paydesk() {
        this.id = idCounter++;
        this.isAvailable = true;
        this.assignedCashier = null;
        this.storedReceipts = new ArrayList<>();
        this.totalAmount = BigDecimal.ZERO;
        this.transactionService = new TransactionService();
    }

    public int getId() {
        return id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Cashier getAssignedCashier() {
        return assignedCashier;
    }

    public void setAssignedCashier(Cashier assignedCashier) {
        this.assignedCashier = assignedCashier;
    }

    public ArrayList<Receipt> getStoredReceipts() {
        return storedReceipts;
    }

    public void setStoredReceipts(ArrayList<Receipt> storedReceipts) {
        this.storedReceipts = storedReceipts;
    }

    public boolean setAvailability() {
        return isAvailable;
    }

    public void setAvailability(boolean available) {
        isAvailable = available;
    }

    public boolean storeReceipt(Receipt receipt) {
        return storedReceipts.add(receipt);
    }
    public void printReceipts() {
        for (Receipt receipt : storedReceipts) {
            System.out.println(receipt);
        }
    }
    public TransactionService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public boolean processTransaction(Customer customer, Inventory inventory) {
        return transactionService.process(customer, inventory, this);
    }
}

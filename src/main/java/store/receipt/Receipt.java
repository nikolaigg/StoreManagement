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

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Receipt.idCounter = idCounter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Cashier getCashierIssuedBy() {
        return cashierIssuedBy;
    }

    public void setCashierIssuedBy(Cashier cashierIssuedBy) {
        this.cashierIssuedBy = cashierIssuedBy;
    }

    public String getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(String issuedOn) {
        this.issuedOn = issuedOn;
    }

    public ArrayList<CartItem> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(ArrayList<CartItem> boughtItems) {
        this.boughtItems = boughtItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", store=" + store +
                ", cashierIssuedBy=" + cashierIssuedBy +
                ", issuedOn='" + issuedOn + '\'' +
                ", boughtItems=" + boughtItems +
                ", totalPrice=" + totalPrice +
                '}';
    }
}


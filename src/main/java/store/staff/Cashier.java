package store.staff;

import customer.Customer;
import exceptions.NoCashierOnPaydeskException;
import store.transaction.Paydesk;
import store.Store;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cashier implements Serializable {
    private static final long serialVersionUID = 2L;
    private static int idCounter = 1;

    private int id;
    private String name;
    private BigDecimal monthlySalary;
    private Paydesk assignedPaydesk;
    private Store store;

    public Cashier(String name, BigDecimal monthlySalary) {
        this.id = idCounter++;
        this.name = name;
        this.monthlySalary = monthlySalary;
        this.assignedPaydesk = null;
        this.store = null;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Paydesk getAssignedPaydesk() {
        return assignedPaydesk;
    }

    public void setAssignedPaydesk(Paydesk assignedPaydesk) {
        this.assignedPaydesk = assignedPaydesk;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void sellProducts(Customer customer) {
        if(assignedPaydesk == null) {
            throw new NoCashierOnPaydeskException("Can't buy from an empty paydesk");
        }

        assignedPaydesk.processTransaction(customer, store.getInventory());

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if( obj == null || getClass() != obj.getClass()) return false;

        Cashier cashier = (Cashier) obj;
        return id == cashier.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
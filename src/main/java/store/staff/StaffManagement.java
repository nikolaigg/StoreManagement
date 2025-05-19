package store.staff;

import store.transaction.Paydesk;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class StaffManagement implements Serializable {
    @Serial
    private static final long serialVersionUID = 9L;

    private ArrayList<Cashier> cashiers;

    public StaffManagement() {
        this.cashiers = new ArrayList<>();
    }

    public boolean hireStaff(Cashier cashier){
        if(!cashiers.contains(cashier)){
            return cashiers.add(cashier);
        }
        return false;
    }

    public boolean fireStaff(Cashier cashier){
        if(cashiers.contains(cashier)){
            return cashiers.remove(cashier);
        }
        return false;
    }

    public void increaseSalary(Cashier cashier, BigDecimal amount){
        if(cashiers.contains(cashier)){
            BigDecimal newSalary = cashier.getMonthlySalary().add(amount);
            cashier.setMonthlySalary(newSalary);
        }
    }

    public BigDecimal totalStaffExpenses(){
         BigDecimal total = BigDecimal.ZERO;
        for(Cashier cashier : cashiers){
            total =  total.add(cashier.getMonthlySalary());
        }
        return total;
    }
    // to do edge cases
    public void assignPaydeskToStaff(Cashier cashier, ArrayList<Paydesk> paydesks){
        if (cashier.getAssignedPaydesk() == null){
            for(Paydesk p : paydesks){
                if(p.setAvailability()) {
                    cashier.setAssignedPaydesk(p);
                    p.setAssignedCashier(cashier);
                    p.setAvailability(false);
                    break;
                }
            }

        }
    }
}

package store.transaction;

import customer.CartItem;
import customer.Customer;
import store.inventory.Inventory;
import store.receipt.Receipt;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static store.receipt.ReceiptFileHandler.writeToFile;

public class TransactionService implements Serializable {
    @Serial
    private static final long serialVersionUID = 10L;

    public boolean process(Customer customer, Inventory inventory, Paydesk paydesk) {
        if(!canCustomerAfford(customer)) return false;

        checkInventory(inventory, customer.getShoppingCart());
        chargeCustomer(customer);
        reduceStock(inventory, customer.getShoppingCart());

        issueReceipt(customer,paydesk);
        return true;
    }
    public void checkInventory(Inventory inventory, ArrayList<CartItem> shoppingCart ) {
        inventory.checkForUnavailableItems(shoppingCart);
        inventory.checkItemAvailability(shoppingCart);
    }

    public void reduceStock(Inventory inventory, ArrayList<CartItem> shoppingCart) {
        for(CartItem cartItem : shoppingCart) {
            inventory.reduceStock(cartItem.getCartItemProduct(),cartItem.getAmount());
        }
    }

    public boolean canCustomerAfford(Customer customer){
        return customer.getWallet().compareTo(customer.shoppingCartTotal()) >= 0;
    }

    public void chargeCustomer(Customer customer){
        customer.setWallet(customer.getWallet().subtract(customer.shoppingCartTotal()));
    }

    public void issueReceipt(Customer customer, Paydesk paydesk){
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String issuedOn = timestamp.format(formatter);

        Receipt receipt = new Receipt(
                paydesk.getAssignedCashier().getStore(),
                paydesk.getAssignedCashier(),
                issuedOn,
                customer.getShoppingCart(),
                customer.shoppingCartTotal()
        );
        paydesk.setTotalAmount(paydesk.getTotalAmount().add(customer.shoppingCartTotal()));
        paydesk.storeReceipt(receipt);
        writeToFile("D:\\JAVA\\JavaDirectory\\Store\\src\\main\\receipts\\receipt" + receipt.getId() + "_" + issuedOn + ".txt", receipt);
    }
}

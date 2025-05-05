package products;


import java.io.Serializable;
import java.math.BigDecimal;

public abstract class DeliveredProduct implements Serializable {
    private static final long serialVersionUID = 6L;
    private static int idCounter = 1;

    private int id;
    private String name;
    private BigDecimal deliveryPrice;
    private BigDecimal sellPrice;
    private int quantity;

    public DeliveredProduct(String name, BigDecimal deliveryPrice, int quantity) {
        this.id = idCounter++;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.sellPrice = BigDecimal.ZERO;
        this.quantity = quantity;
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

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                ", sellPrice=" + sellPrice +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if( obj == null || getClass() != obj.getClass()) return false;

        DeliveredProduct deliveredProduct = (DeliveredProduct) obj;
        return id == deliveredProduct.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

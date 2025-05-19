package products;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public abstract class DeliveredProduct implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;
    private static int idCounter = 1;

    private int id;
    private String name;
    private BigDecimal deliveryPrice;
    private BigDecimal sellPrice;

    public DeliveredProduct(String name, BigDecimal deliveryPrice) {
        this.id = idCounter++;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.sellPrice = BigDecimal.ZERO;
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



    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                ", sellPrice=" + sellPrice +
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

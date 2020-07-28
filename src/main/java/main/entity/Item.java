package main.entity;

import java.math.BigDecimal;

public class Item {

    private String id;
    private Integer quantity;
    private BigDecimal price;

    public Item(String id, Integer quantity, BigDecimal price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public String toString() {
        return "Item(id=" + this.getId() + ", quantity=" + this.getQuantity() + ", price=" + this.getPrice() + ")";
    }
}

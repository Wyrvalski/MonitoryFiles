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

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String toString() {
        return "Item(id=" + this.getId() + ", quantity=" + this.getQuantity() + ", price=" + this.getPrice() + ")";
    }
}

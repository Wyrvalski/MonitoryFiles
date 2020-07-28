package main.entity;

import java.math.BigDecimal;
import java.util.List;

public class Sale {

    private String id;
    private List<Item> items;
    private Salesman salesman;
    private BigDecimal totalSale;

    public Sale(String id, List<Item> items, Salesman salesman) {
        this.id = id;
        this.items = items;
        this.salesman = salesman;
        this.totalSale = setTotalSale(items);
    }

    public Sale() {

    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public BigDecimal setTotalSale(List<Item> items) {
        BigDecimal total = new BigDecimal("0.00");
        for (int i = 0; i < items.size(); i++){
            total = total.add(items.get(i).getPrice());
        }
        return this.totalSale = total;
    }

    public String toString() {
        return "Sale(id=" + this.getId() + ", items=" + this.getItems() + ", vendedor=" + this.getSalesman().getName() + "total: " + this.getTotalSale() +")";
    }


}

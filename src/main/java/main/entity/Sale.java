package main.entity;

import java.math.BigDecimal;
import java.util.List;

public class Sale {

    private Integer groupId;
    private String id;
    private List<Item> items;
    private Salesman salesman;
    private BigDecimal totalSale;

    public Sale(Integer groupId, String id, List<Item> items, Salesman salesman) {
        this.groupId = groupId;
        this.id = id;
        this.items = items;
        this.salesman = salesman;
        setTotalSale(items);
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

    public void setTotalSale(List<Item> items) {
        BigDecimal total = new BigDecimal("0.00");
        for (int i = 0; i < items.size(); i++){
            total = total.add(items.get(i).getPrice());
        }
        this.totalSale = total;
    }

    public String toString() {
        return "Sale(id=" + this.getId() + ", items=" + this.getItems() + ", vendedor=" + this.getSalesman().getName() + "total: " + this.getTotalSale() +")";
    }


}

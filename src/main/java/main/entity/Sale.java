package main.entity;

import java.util.List;

public class Sale {

    private Integer groupId;
    private String id;
    private List<Item> items;
    private Salesman salesman;

    public Sale(Integer groupId, String id, List<Item> items, Salesman salesman) {
        this.groupId = groupId;
        this.id = id;
        this.items = items;
        this.salesman = salesman;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }
    public String toString() {
        return "Sale(id=" + this.getId() + ", items=" + this.getItems() + ", vendedor=" + this.getSalesman().getName() + ")";
    }
}

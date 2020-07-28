package main.entity;

import java.math.BigDecimal;

public class SalesmanForSales {

    private String salesman;
    private BigDecimal total;

    public SalesmanForSales() {

    }

    public SalesmanForSales(String salesman, BigDecimal total) {
        this.salesman = salesman;
        this.total = total;
    }

    public String getSalesman() {
        return salesman;
    }

    public BigDecimal getTotal() {
        return total;
    }

}

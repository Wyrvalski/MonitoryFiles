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

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

package main.entity;

import java.math.BigDecimal;

public class Salesman {

    private Integer gorupId;
    private String name;
    private String cpf;
    private BigDecimal salary;

    public Salesman() {

    }

    public Salesman(Integer gorupId, String name, String cpf, BigDecimal salary) {
        this.gorupId = gorupId;
        this.name = name;
        this.cpf = cpf;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getSalary() {
        return salary;
    }


    public String toString() {
        return "Salesman(name=" + this.getName() + ", cpf=" + this.getCpf() + ", salary=" + this.getSalary() + ")";
    }
}

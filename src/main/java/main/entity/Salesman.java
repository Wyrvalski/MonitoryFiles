package main.entity;

import java.math.BigDecimal;

public class Salesman {

    private Integer gorupId;
    private String name;
    private String cpf;
    private BigDecimal salary;

    public Salesman(Integer gorupId, String name, String cpf, BigDecimal salary) {
        this.gorupId = gorupId;
        this.name = name;
        this.cpf = cpf;
        this.salary = salary;
    }

    public Integer getGorupId() {
        return gorupId;
    }

    public void setGorupId(Integer gorupId) {
        this.gorupId = gorupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String toString() {
        return "Salesman(name=" + this.getName() + ", cpf=" + this.getCpf() + ", salary=" + this.getSalary() + ")";
    }
}

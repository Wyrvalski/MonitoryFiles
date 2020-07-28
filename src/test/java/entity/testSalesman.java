package entity;

import main.entity.Salesman;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class testSalesman {

    private String name = "Eduardo";
    private String cpf = "12345678912";
    private BigDecimal salary = new BigDecimal("2300.00");

    @Test
    public void testSalesmanGetName() {
        Salesman salesman = new Salesman(name,cpf,salary);
        Assert.assertEquals(salesman.getName(),name);
    }

    @Test
    public void testSalesmanGetCpf() {
        Salesman salesman = new Salesman(name,cpf,salary);
        Assert.assertEquals(salesman.getCpf(),cpf);
    }

    @Test
    public void testSalesmanGetArea() {
        Salesman salesman = new Salesman(name,cpf,salary);
        Assert.assertEquals(salesman.getSalary(),salary);
    }

}

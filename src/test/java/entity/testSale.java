package entity;
import main.entity.Item;
import main.entity.Sale;
import main.entity.Salesman;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testSale {
    private String id = "10";
    private List<Item> items = new ArrayList<>();
    private Salesman salesman;
    private BigDecimal totalSale;


    @Before
    public void setup(){
        salesman = new Salesman("Eduardo","12345678910",new BigDecimal("1000.00"));
        Item item1 = new Item("1",Integer.parseInt("10"),new BigDecimal( "100"));
        Item item2 = new Item("2",Integer.parseInt("30"),new BigDecimal( "2.50"));
        Item item3 = new Item("3",Integer.parseInt("40"),new BigDecimal( "3.10"));
        items.add(item1);
        items.add(item2);
        items.add(item3);
    }

    @Test
    public void testSaleGetId() {
        Sale sale = new Sale(id,items,salesman);
        Assert.assertEquals(sale.getId(),id);
    }

    @Test
    public void testSaleGetItems() {
        Sale sale = new Sale(id,items,salesman);
        Assert.assertTrue(Arrays.equals(items.toArray(),sale.getItems().toArray()));
    }

    @Test
    public void testSaleGetSalesman() {
        Sale sale = new Sale(id,items,salesman);
        Assert.assertEquals(sale.getSalesman().getName(),salesman.getName());
    }

    @Test
    public void testSaleGetTotalSale() {
        Sale sale = new Sale(id,items,salesman);
        Assert.assertEquals(sale.getTotalSale(),new BigDecimal("105.60"));
    }
}

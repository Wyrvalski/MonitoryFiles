package entity;
import main.entity.Item;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class testItem {
    public String id = "1";
    public Integer quantity = 10;
    public BigDecimal price = new BigDecimal("12.30");

    @Test
    public void testItemGetId() {
        Item item = new Item(id,quantity,price);
        Assert.assertEquals(item.getId(),id);
    }

    @Test
    public void testItemGetQuantity() {
        Item item = new Item(id,quantity,price);
        Assert.assertEquals(item.getQuantity(),quantity);
    }

    @Test
    public void testItemGetPrice() {
        Item item = new Item(id,quantity,price);
        Assert.assertEquals(item.getPrice(),price);
    }
}

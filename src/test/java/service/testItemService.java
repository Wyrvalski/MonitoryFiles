package service;

import main.entity.Item;
import main.service.ItemService;
import main.service.SaleService;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class testItemService {
    private String sale = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";

    @Test
    public void testGetAllItemsInLine(){
        ItemService itemService = new ItemService();
        String items = itemService.getItemsInLine(sale);
        Assert.assertEquals(items,"1-34-10,2-33-1.50,3-40-0.10");
    }

}

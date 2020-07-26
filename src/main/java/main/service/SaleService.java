package main.service;
import main.entity.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleService {


    public List<Item> mountItensInSale( String line ) {
        ItemService itemService = new ItemService();
        String itens = itemService.getItemsInLine(line);
        String[] separatorItens = itens.split(",");
        List<Item> arrayItems = new ArrayList<>();

        for (int i = 0; i < separatorItens.length; i ++) {
            String[] partesItems = separatorItens[i].split("-");
            arrayItems.add(new Item(partesItems[0],Integer.parseInt(partesItems[1]),new BigDecimal(partesItems[2])));
        }
        return arrayItems;
    }
}

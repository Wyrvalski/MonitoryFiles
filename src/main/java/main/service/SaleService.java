package main.service;
import main.entity.Item;
import main.entity.Sale;
import main.entity.Salesman;

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

    public List<Sale> getAllSale(List<Object> allDataInFile) {
        List<Sale> sales = new ArrayList<>();
        for (int i =0; i < allDataInFile.size(); i++) {
            if (allDataInFile.get(i) instanceof Sale) {
                sales.add((Sale) allDataInFile.get(i));
            }
        }
        return sales;
    }

    public Sale getBiggerSale(List<Object> allDataInFile) {
        List<Sale> sales = getAllSale(allDataInFile);
        Sale biggerSale = new Sale();
        biggerSale = sales.get(0);
        for (int i = 0; i < sales.size(); i++ ) {
            int res = biggerSale.getTotalSale().compareTo(sales.get(i).getTotalSale());
            if(res == -1) {
                biggerSale = sales.get(i);
            }
        }
        return biggerSale;
    }
}

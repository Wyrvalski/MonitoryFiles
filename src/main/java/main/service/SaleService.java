package main.service;
import main.entity.Item;
import main.entity.Sale;
import main.entity.Salesman;
import main.entity.SalesmanForSales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleService {
    List<Salesman> salesmen = new ArrayList<>();

    public List<Item> mountItensInSale( String line ) {
        String itens = ItemService.getItemsInLine(line);
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

    public List<SalesmanForSales> getAllSalesForSalesman (List<Object> allDataInFile) {
        List <Salesman> salesmen = SalesmanService.getAllSalesman(allDataInFile);
        List<Sale> sales = getAllSale(allDataInFile);
        BigDecimal soma = new BigDecimal("0.00");
        List<SalesmanForSales> salesmanForSales = new ArrayList<>();
        for (int i = 0; i < salesmen.size(); i++) {
            for (int j = 0; j < sales.size(); j++) {
                if (salesmen.get(i).getName().equals(sales.get(j).getSalesman().getName())) {
                    soma = soma.add(sales.get(j).getTotalSale());
                }
            }
            salesmanForSales.add(new SalesmanForSales(salesmen.get(i).getName(),soma));
            soma = BigDecimal.ZERO;

        }
        return salesmanForSales;
    }

    public Salesman getWorstSalesman(List<Object> allDataInFile) {
        List<SalesmanForSales> salesmanForSales = getAllSalesForSalesman(allDataInFile);
        List <Salesman> salesmen = SalesmanService.getAllSalesman(allDataInFile);
        SalesmanService salesmanService = new SalesmanService();
        String worstSalesmanName = salesmanForSales.get(0).getSalesman();
        BigDecimal total = salesmanForSales.get(0).getTotal();
        for (int i = 0; i < salesmanForSales.size(); i++) {
                if (total.doubleValue() > salesmanForSales.get(i).getTotal().doubleValue()) {
                    total = salesmanForSales.get(i).getTotal();
                    worstSalesmanName = salesmanForSales.get(i).getSalesman();

                }
        }

        Salesman worstSalesman = salesmanService.getSalesmanByName(worstSalesmanName,salesmen);
        return worstSalesman;
    }
}

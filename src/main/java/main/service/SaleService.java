package main.service;
import main.entity.Item;
import main.entity.Sale;
import main.entity.Salesman;
import main.entity.SalesmanForSales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleService {

    public List<Sale> sales;
    public List<SalesmanForSales> salesmanForSales = new ArrayList<>();
    public List<Salesman> salesmen = new ArrayList<>();
    public SalesmanService salesmanService = new SalesmanService();
    public String items;
    public SaleService() {

    }

    public SaleService(List<Object> allDataInFile) {
        this.sales = getAllSale(allDataInFile);
        this.salesmen = salesmanService.getAllSalesman(allDataInFile);
        this.salesmanForSales = getAllSalesForSalesman(allDataInFile);
    }

    public List<Item> mountItensInSale( String line ) {
        this.items = ItemService.getItemsInLine(line);
        String[] separatorItens = this.items.split(",");
        List<Item> arrayItems = new ArrayList<>();

        for (int i = 0; i < separatorItens.length; i ++) {
            String[] partesItems = separatorItens[i].split("-");
            arrayItems.add(new Item(partesItems[0],Integer.parseInt(partesItems[1]),new BigDecimal(partesItems[2])));
        }
        return arrayItems;
    }

    public List<Sale> getAllSale(List<Object> allDataInFile) {
        List<Sale> listSales = new ArrayList<>();
        for (int i =0; i < allDataInFile.size(); i++) {
            if (allDataInFile.get(i) instanceof Sale) {
                listSales.add((Sale) allDataInFile.get(i));
            }
        }
        return listSales;
    }

    public Sale getBiggerSale(List<Object> allDataInFile) {
        this.sales = getAllSale(allDataInFile);
        Sale biggerSale = new Sale();
        biggerSale = this.sales.get(0);
        for (int i = 0; i < this.sales.size(); i++ ) {
            int res = biggerSale.getTotalSale().compareTo(this.sales.get(i).getTotalSale());
            if(res == -1) {
                biggerSale = this.sales.get(i);
            }
        }
        return biggerSale;
    }

    public List<SalesmanForSales> getAllSalesForSalesman (List<Object> allDataInFile) {
        BigDecimal soma = new BigDecimal("0.00");
        for (int i = 0; i < this.salesmen.size(); i++) {
            for (int j = 0; j < this.sales.size(); j++) {
                if (this.salesmen.get(i).getName().equals(this.sales.get(j).getSalesman().getName())) {
                    soma = soma.add(this.sales.get(j).getTotalSale());
                }
            }
            this.salesmanForSales.add(new SalesmanForSales(this.salesmen.get(i).getName(),soma));
            soma = BigDecimal.ZERO;

        }
        return this.salesmanForSales;
    }

    public Salesman getWorstSalesman() {
        System.out.println(this.salesmanForSales.get(0).getSalesman());
        String worstSalesmanName = this.salesmanForSales.get(0).getSalesman();
        BigDecimal total = this.salesmanForSales.get(0).getTotal();
        for (int i = 0; i < this.salesmanForSales.size(); i++) {
                if (total.doubleValue() > this.salesmanForSales.get(i).getTotal().doubleValue()) {
                    total = this.salesmanForSales.get(i).getTotal();
                    worstSalesmanName = this.salesmanForSales.get(i).getSalesman();
                }
        }
        Salesman worstSalesman = salesmanService.getSalesmanByName(worstSalesmanName,salesmen);
        return worstSalesman;
    }
}

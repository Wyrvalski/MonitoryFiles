package main.service;
import main.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleService {

    public List<Sale> sales;
    public List<SalesmanForSales> salesmanForSales = new ArrayList<>();
    public List<Salesman> salesmen = new ArrayList<>();
    public List<Item> items = new ArrayList<>();
    public Salesman salesman;
    public SalesmanService salesmanService = new SalesmanService();
    public Logger logger = LoggerFactory.getLogger(ClientService.class);


    public SaleService() {

    }

    public SaleService(List<Object> allDataInFile) {
        this.sales = getAllSale(allDataInFile);
        this.salesmen = salesmanService.getAllSalesman(allDataInFile);
        this.salesmanForSales = getAllSalesForSalesman(allDataInFile);
    }

    public Boolean salesExists(String id, List<Sale> sales) {
        for (int i = 0; i < sales.size(); i++) {
            if (sales.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Sale createSale(String[] parte, List<Object> allDataInFile, int lineNumber) {
        this.sales = getAllSale(allDataInFile);
        this.salesmen = salesmanService.getAllSalesman(allDataInFile);
        this.salesman = salesmanService.getSalesmanByName(parte[3], this.salesmen);
        this.items = mountItensInSale(parte[2]);
        if ( !salesExists(parte[1],this.sales)){
            return new Sale(parte[1],this.items,this.salesman);
        }
        this.logger.info("Salee da linha " + lineNumber + " já está cadastrado");
        return null;
    }

    public List<Item> mountItensInSale( String line ) {
        String items = ItemService.getItemsInLine(line);
        String[] separatorItens = items.split(",");
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

    //Buscar o total de todas as vendas de cada vendedor
    public List<SalesmanForSales> getAllSalesForSalesman (List<Object> allDataInFile) {
        BigDecimal soma = new BigDecimal("0.00");
        List<Salesman> salesmen = salesmanService.getAllSalesman(allDataInFile);

        List<Sale> sales = getAllSale(allDataInFile);

        for (int i = 0; i < salesmen.size(); i++) {
            for (int j = 0; j < sales.size(); j++) {
                if (salesmen.get(i).getName().equals(sales.get(j).getSalesman().getName())) {
                    soma = soma.add(sales.get(j).getTotalSale());
                }
            }

            salesmanForSales.add(new SalesmanForSales(salesmen.get(i).getName(),soma));

            soma = BigDecimal.ZERO;

        }
        return this.salesmanForSales;
    }

    public Salesman getWorstSalesman() {
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

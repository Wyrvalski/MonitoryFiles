package main.service;

import main.entity.Client;
import main.entity.Item;
import main.entity.Sale;
import main.entity.Salesman;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFilesServices {

    public List<Object> readEachFile(Path inDirectory, WatchEvent<?> event) {
        try {
            List<String> lines = Files.readAllLines(inDirectory.resolve((Path) event.context()));
            return mountObjects(lines);
        } catch (IOException ex) {

        }
        return null;
    }

    public List<Object> mountObjects(List<String> lines) {
        SaleService saleService = new SaleService();
        SalesmanService salesmanService = new SalesmanService();
        List<Object> allDataInFile = new ArrayList<>();
        for (String line : lines) {
            String id = line.substring(0, 3);
            String[] parte = line.split("ç");
            switch (id) {
                case "001" :
                    allDataInFile.add(new Salesman(Integer.parseInt(parte[0]),parte[2],parte[1],new BigDecimal(parte[3])));
                    break;
                case "002":
                    allDataInFile.add(new Client(Integer.parseInt(parte[0]),parte[2],parte[1],parte[3]));
                    break;
                case "003":
                    List<Salesman> salesmen = allDataInFile.stream().filter( salesman -> salesman instanceof  Salesman ).map( salesman -> (Salesman) salesman).collect(Collectors.toList());
                    List<Item> arrayItems = saleService.mountItensInSale(line);
                    allDataInFile.add(new Sale(Integer.parseInt(parte[0]),parte[1],arrayItems,salesmanService.getSalesmanByName(parte[3],salesmen)));
                default:
                    break;
            }
        };
        return allDataInFile;
    }
}

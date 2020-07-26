package main.service;

import main.MonitoryFiles;
import main.entity.Client;
import main.entity.Item;
import main.entity.Sale;
import main.entity.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFilesServices {
    Logger logger = LoggerFactory.getLogger(ReadFilesServices.class);
    public List<Object> readEachFile(Path inDirectory, WatchEvent<?> event) {
        try {
            List<String> lines = Files.readAllLines(inDirectory.resolve((Path) event.context()));
            return mountObjects(lines);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
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
                    break;
                default:
                    logger.warn("a linha " + line + " não inicia com nenhum id valido");
                    break;
            }
        };
        return allDataInFile;
    }
}

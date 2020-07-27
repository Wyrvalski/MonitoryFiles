package main.service;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReadFilesServices {
    SaleService saleService = new SaleService();
    SalesmanService salesmanService = new SalesmanService();
    List<Object> allDataInFile = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(ReadFilesServices.class);

    public List<String> readEachFile(Path inDirectory, String event) {
        try {
            List<String> lines = Files.readAllLines(inDirectory.resolve(event),ISO_8859_1);
            return lines;
        } catch (IOException ex) {
            this.logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Object> mountObjects(Path inDirectory, String event) {
        List<String> lines = readEachFile(inDirectory,event);

        for (String line : lines) {
            String id = line.substring(0, 3);
            String[] parte = line.split("ç");
            switch (id) {
                case "001" :
                    this.allDataInFile.add(new Salesman(Integer.parseInt(parte[0]),parte[2],parte[1],new BigDecimal(parte[3])));
                    break;
                case "002":
                    this.allDataInFile.add(new Client(Integer.parseInt(parte[0]),parte[2],parte[1],parte[3]));
                    break;
                case "003":
                    List<Salesman> salesmen = this.allDataInFile.stream().filter( salesman -> salesman instanceof  Salesman ).map( salesman -> (Salesman) salesman).collect(Collectors.toList());
                    List<Item> arrayItems = this.saleService.mountItensInSale(line);
                    this.allDataInFile.add(new Sale(Integer.parseInt(parte[0]),parte[1],arrayItems,this.salesmanService.getSalesmanByName(parte[3],salesmen)));
                    break;
                default:
                    this.logger.warn("a linha " + line + " não inicia com nenhum id valido");
                    break;
            }
        };
        return this.allDataInFile;
    }

    public Sale getBiggerSale(List<Object> allDataInFile) {
        Sale sale = this.saleService.getBiggerSale(allDataInFile);
        return sale;
    }
}

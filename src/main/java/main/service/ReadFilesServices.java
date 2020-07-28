package main.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class ReadFilesServices {

    public SaleService saleService = new SaleService();
    public SalesmanService salesmanService = new SalesmanService();
    public ClientService clientService = new ClientService();
    public List<Object> allDataInFile = new ArrayList<>();
    public Logger logger = LoggerFactory.getLogger(ReadFilesServices.class);

    public List<String> readEachFile(Path inDirectory, String event) {
        Charset charSet = null;
        if (System.getProperty("os.name").contains("Windows")) {
            charSet = ISO_8859_1;
        }
        try {
            List<String> lines = Files.readAllLines(inDirectory.resolve(event), charSet);
            return lines;
        } catch (IOException ex) {
            this.logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Object> mountObjects(Path inDirectory, String event) {
        List<String> lines = readEachFile(inDirectory,event);
        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            String id = line.substring(0, 3);
            String[] parte = line.split("ç");
            switch (id) {
                case "001" :
                    this.allDataInFile.add(salesmanService.createSalesman(parte,this.allDataInFile, lineNumber));
                    break;
                case "002":
                    this.allDataInFile.add(clientService.createClient(parte,this.allDataInFile, lineNumber));
                    break;
                case "003":
                    this.allDataInFile.add(saleService.createSale(parte,this.allDataInFile,lineNumber));
                    break;
                default:
                    this.logger.warn("a linha " + line + " não inicia com nenhum id valido");
                    break;
            }
        };
        lineNumber = 0;
        return this.allDataInFile;
    }
}

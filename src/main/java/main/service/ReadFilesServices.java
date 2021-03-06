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
        List<String> lines = new ArrayList<>();
        try {
        if (System.getProperty("os.name").contains("Windows")) {
            lines = Files.readAllLines(inDirectory.resolve(event), ISO_8859_1) ;
        } else {
            lines = Files.readAllLines(inDirectory.resolve(event)) ;
        }
            return lines;
        } catch (IOException ex) {
            this.logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Object> mountObjects(Path inDirectory, String event) {
        List<String> lines = readEachFile(inDirectory,event);
        int lineNumber = 0;
        try {
            for (int i = 0; i < lines.size(); i++) {
                lineNumber++;
                String id = lines.get(i).substring(0, 3);
                String[] parte =  lines.get(i).split("ç");
                String line = lines.get(i).replace("\n","");
                while (parte.length < 4) {
                    line = line  + lines.get(i+1);
                    parte = line.split("ç");
                    i++;
                    lineNumber++;
                }
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
                        this.logger.warn("a linha " + lines.get(i) + " não inicia com nenhum id valido");
                        break;
                }
            };
        } catch ( Exception ex) {
            this.logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }

        lineNumber = 0;
        return this.allDataInFile;
    }
}

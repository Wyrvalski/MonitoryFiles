package main.service;
import main.entity.Client;
import main.entity.Salesman;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class WriteFilesService {
    private ClientService clientService;
    private SaleService saleService ;
    private SalesmanService salesmanService = new SalesmanService();
    private List<Object> allDataInFile;

    public WriteFilesService(List<Object> allDataInFile) {
        this.clientService = new ClientService();
        this.saleService = new SaleService(allDataInFile);
        this.allDataInFile = allDataInFile;
    }


    public void writeOnFile (Path outDirectory, WatchEvent<?> event) {

        String filename = LocalDate.now().format(DateTimeFormatter.ofPattern("d-MM-uuuu")) + "-RelatiorioDetalhado.done.dat";
        Charset charset = UTF_8;
        if (System.getProperty("os.name").contains("Windows")){
            charset = ISO_8859_1;
        }
        try (BufferedWriter writer = Files.newBufferedWriter(outDirectory.resolve(filename), charset)) {
            writer.write(mountOutPutFile(this.allDataInFile));
        } catch (IOException ex) {

        }
    }

    public String mountOutPutFile(List<Object> allDataInFile) {
        List<Salesman> salesmen = salesmanService.getAllSalesman(allDataInFile);
        List<Client> clients = clientService.getAllClient(allDataInFile);
        Salesman salesman = saleService.getWorstSalesman();
        String id = saleService.getBiggerSale(allDataInFile).getId();
        return String.format("%oç%oç%sç%s",clients.size(),salesmen.size(),id,salesman.getName());
    }
}

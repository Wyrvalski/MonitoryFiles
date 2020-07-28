package main.service;
import main.entity.Client;
import main.entity.Salesman;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.List;

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
        try (BufferedWriter writer = Files.newBufferedWriter(outDirectory.resolve("Vamosver.dat"))) {
            writer.write(mountOutPutFile(this.allDataInFile));
        } catch (IOException ex) {

        }
    }

    public String mountOutPutFile(List<Object> allDataInFile) {
        List<Salesman> salesmen = salesmanService.getAllSalesman(allDataInFile);
        List<Client> clients = clientService.getAllClient(allDataInFile);
        Salesman salesman = saleService.getWorstSalesman();
        String id = saleService.getBiggerSale(allDataInFile).getId();
        System.out.println(clients.size());
        System.out.printf("%o", clients.size());
        return String.format("%oç%oç%sç%s",clients.size(),salesmen.size(),id,salesman.getName());
    }
}

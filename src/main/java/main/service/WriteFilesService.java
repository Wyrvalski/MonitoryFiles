package main.service;

import main.entity.Client;
import main.entity.Item;
import main.entity.Sale;
import main.entity.Salesman;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class WriteFilesService {
    ClientService clientService = new ClientService();
    List<Item> items = new ArrayList<>();
    SaleService saleService = new SaleService();
    SalesmanService salesmanService = new SalesmanService();

    public void writeOnFile (Path outDirectory, WatchEvent<?> event, List<Object> textFile) {
        try (BufferedWriter writer = Files.newBufferedWriter(outDirectory.resolve(event.context().toString().replace(".dat", ".done.dat")),ISO_8859_1)) {
            writer.write(mountOutPutFile(textFile));
        } catch (IOException ex) {

        }
    }

    public String mountOutPutFile(List<Object> allDataInFile) {
        List<Salesman> salesmen = salesmanService.getAllSalesman(allDataInFile);
        List<Client> clients = clientService.getAllClient(allDataInFile);
        Salesman salesman = saleService.getWorstSalesman(allDataInFile);
        String id = saleService.getBiggerSale(allDataInFile).getId();
        return String.format("%oç%oç%sç%s",clients.size(),salesmen.size(),id,salesman.getName());
    }

}

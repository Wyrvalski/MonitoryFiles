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

public class WriteFilesService {
    List<Client> clients = new ArrayList<>();
    List<Item> items = new ArrayList<>();
    List<Sale> sales = new ArrayList<>();
    List<Salesman> salesmen = new ArrayList<>();

    public void writeOnFile (Path outDirectory, WatchEvent<?> event, List<Object> textFile) {
        try (BufferedWriter writer = Files.newBufferedWriter(outDirectory.resolve(event.context().toString().replace(".dat", ".done.dat")))) {
            SalesmanService salesmanService = new SalesmanService();
            this.salesmen = salesmanService.getAllSalesman(textFile);
            System.out.println(salesmen.get(0).toString());
            writer.write("Teste");
        } catch (IOException ex) {

        }
    }

}

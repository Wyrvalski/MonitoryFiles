package main.service;
import main.entity.Client;
import main.entity.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public Logger logger = LoggerFactory.getLogger(WriteFilesService.class);

    public WriteFilesService(List<Object> allDataInFile) {
        this.clientService = new ClientService();
        this.saleService = new SaleService(allDataInFile);
        this.allDataInFile = allDataInFile;
    }


    public void writeOnFile (Path outDirectory, WatchEvent<?> event) {

        String filename ="-Relatiorio.done.dat";
        String filename2 = "RelatórioDetalhado.done.dat";
        Charset charset = UTF_8;
        if (System.getProperty("os.name").contains("Windows")){
            charset = ISO_8859_1;
        }
        try (BufferedWriter writer = Files.newBufferedWriter(outDirectory.resolve(filename), charset)) {
            writer.write(mountOutPutFile(this.allDataInFile));
        } catch (IOException ex) {
            this.logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        try (BufferedWriter writer = Files.newBufferedWriter(outDirectory.resolve(filename2), charset)) {
            writer.write(mountOutPutFileDetails(this.allDataInFile));
        } catch (IOException ex) {
            this.logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String mountOutPutFile(List<Object> allDataInFile) {
        List<Salesman> salesmen = salesmanService.getAllSalesman(allDataInFile);
        List<Client> clients = clientService.getAllClient(allDataInFile);
        Salesman salesman = saleService.getWorstSalesman();
        String id = saleService.getBiggerSale(allDataInFile).getId();
        return String.format("%oç%oç%sç%s",clients.size(),salesmen.size(),id,salesman.getName());
    }
    public String mountOutPutFileDetails(List<Object> allDataInFile) {
        List<Salesman> salesmen = salesmanService.getAllSalesman(allDataInFile);
        List<Client> clients = clientService.getAllClient(allDataInFile);
        Salesman salesman = saleService.getWorstSalesman();
        String id = saleService.getBiggerSale(allDataInFile).getId();
        String salesmanBiggerSale = saleService.getBiggerSale(allDataInFile).getSalesman().getName();

        return String.format("Quantidade de clientes cadastrados = %o \n\n" +
                "Quantiade de vendedores cadastrados = %o \n\n" +
                "Id da maior venda = %s \n\n" +
                "Vendedor da maior venda = %s \n\n" +
                "Pior vendedor = %s",clients.size(),salesmen.size(),id,salesmanBiggerSale,salesman.getName());
    }
}

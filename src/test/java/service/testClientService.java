package service;

import main.MonitoryFiles;
import main.entity.Client;
import main.entity.Item;
import main.entity.Sale;
import main.service.ClientService;
import main.service.ReadFilesServices;
import main.service.SaleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class testClientService {
    private List<Sale> sales = new ArrayList<>();
    private List<String> lines = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private Path inDirectory = MonitoryFiles.createPath("in");
    private File file = new File(inDirectory + File.separator + "arquivoTeste.dat");
    ReadFilesServices readFilesServices = new ReadFilesServices();


    @Before
    public void setup(){
        lines.add("001ç1234567891234çPedroç50000");
        lines.add("001ç1234567899994çLucasç50000");
        lines.add("001ç1276567899994çLucioç50000");
        lines.add("002ç2345675434544345çJose da SilvaçRural");
        lines.add("002ç2345235434544345çPietroçIndustrial");
        lines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        lines.add("003ç18ç[1-10-50,2-30-10,3-40-20]çPedro");
        lines.add("003ç12ç[1-10-120,2-30-2.50,3-40-3.10]çLucas");
        lines.add("003ç13ç[1-10-90,2-30-2.50,3-40-3.10]çLucio");
        Item item1 = new Item("1",Integer.parseInt("10"),new BigDecimal( "100"));
        Item item2 = new Item("2",Integer.parseInt("30"),new BigDecimal( "2.50"));
        Item item3 = new Item("3",Integer.parseInt("40"),new BigDecimal( "3.10"));

        items.add(item1);
        items.add(item2);
        items.add(item3);
        try {
            file.createNewFile();
            BufferedWriter writer = Files.newBufferedWriter(inDirectory.resolve(file.toString()));
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i) + '\n');
            }
            writer.close();
        } catch (IOException ex) {

        }
    }

    @Test
    public void testGetAllClientsFromFile() {
        ReadFilesServices readFilesServices = new ReadFilesServices();
        ClientService clientService = new ClientService();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        List<Client> clients = clientService.getAllClient(allDataInFile);
        Assert.assertEquals(clients.size(),2);
    }

    @Test
    public void testIfClientExistsBeforeCreate() {
        ReadFilesServices readFilesServices = new ReadFilesServices();
        ClientService clientService = new ClientService();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        List<Client> clients = clientService.getAllClient(allDataInFile);
        boolean clientExists = clientService.clientExists("2345675434544345",clients);
        Assert.assertTrue(clientExists);
        clientExists = clientService.clientExists("0000000000000000",clients);
        Assert.assertFalse(clientExists);
    }

    @Test
    public void testCreateClient() {
        List<Object> allDataInFile = new ArrayList<>();
        ClientService clientService = new ClientService();
        String line = "002ç2345675434544345çJose da SilvaçRural";
        String[] parte= line.split("ç");
        int lineNumber = 0;
        Client client = clientService.createClient(parte,allDataInFile, lineNumber);
        Assert.assertEquals(client.getName(),"Jose da Silva");
    }

}

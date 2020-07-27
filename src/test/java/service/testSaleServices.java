package service;

import main.MonitoryFiles;
import main.entity.Item;
import main.entity.Sale;
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

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class testSaleServices {
    private List<String> lines = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    Path inDirectory = MonitoryFiles.createPath("in");
    File file = new File(inDirectory + File.separator + "arquivoTeste.dat");

    @Before
    public void setup(){
        lines.add("001ç1234567891234çPedroç50000");
        lines.add("002ç2345675434544345çJose da SilvaçRural");
        lines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        lines.add("003ç10ç[1-10-120,2-30-2.50,3-40-3.10]çPedro");
        Item item1 = new Item("1",Integer.parseInt("10"),new BigDecimal( "100"));
        Item item2 = new Item("2",Integer.parseInt("30"),new BigDecimal( "2.50"));
        Item item3 = new Item("3",Integer.parseInt("40"),new BigDecimal( "3.10"));
        items.add(item1);
        items.add(item2);
        items.add(item3);
        try {
            file.createNewFile();
            BufferedWriter writer = Files.newBufferedWriter(inDirectory.resolve(file.toString()),ISO_8859_1);
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i) + '\n');
            }
            writer.close();
        } catch (IOException ex) {

        }
    }

    @Test
    public void testMountItensFromStringToArrayOfItems() {
        SaleService saleService = new SaleService();
        List<Item> saleItem = saleService.mountItensInSale("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        Assert.assertEquals(saleItem.get(0).toString(),items.get(0).toString());
    }

    @Test
    public void testGetAllSalesInObject() {
        ReadFilesServices readFilesServices = new ReadFilesServices();
        SaleService saleService = new SaleService();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        List<Sale> sales = saleService.getAllSale(allDataInFile);
        Assert.assertEquals(sales.size(), 2);

    }

}

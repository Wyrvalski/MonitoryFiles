package service;

import main.MonitoryFiles;
import main.entity.Item;
import main.entity.Sale;
import main.entity.Salesman;
import main.entity.SalesmanForSales;
import main.service.ReadFilesServices;
import main.service.SaleService;
import org.junit.After;
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

    @After
    public void deleteFile() {
        file.delete();
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
        Assert.assertEquals(sales.size(), 4);

    }

    @Test
    public void testIfExistsSaleBeforeCreate() {
        SaleService saleService = new SaleService();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        this.sales = saleService.getAllSale(allDataInFile);
        Assert.assertTrue(saleService.salesExists("18", this.sales));
        Assert.assertFalse(saleService.salesExists("11", this.sales));
    }

    @Test
    public void testCreateNewSale() {
        SaleService saleService = new SaleService();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        String line = "003ç11ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        String[] parte = line.split("ç");
        int lineNumber = 0;
        Sale sale = saleService.createSale(parte,allDataInFile,lineNumber);
        Assert.assertNotNull(sale);
    }

    @Test
    public void testGetBiggerSale() {
        SaleService saleService = new SaleService();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        Sale sale = saleService.getBiggerSale(allDataInFile);
        Assert.assertEquals("18",sale.getId());
    }

    @Test
    public void testGetAllSalesFromEachSalesman(){
        SaleService saleService = new SaleService();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        List<SalesmanForSales> salesmanForSales = saleService.getAllSalesForSalesman(allDataInFile);
        Assert.assertEquals( salesmanForSales.get(0).getTotal(),new BigDecimal("2799.00"));
    }

    @Test
    public void testGetWorstSalesman(){
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        SaleService saleService = new SaleService(allDataInFile);
        Salesman salesman = saleService.getWorstSalesman();
        Assert.assertEquals( salesman.getName(),"Lucio");
    }

}

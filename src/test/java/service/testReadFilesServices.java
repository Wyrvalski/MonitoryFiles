package service;

import main.MonitoryFiles;
import main.entity.Client;
import main.entity.Item;
import main.entity.Sale;
import main.entity.Salesman;
import main.service.ReadFilesServices;
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

public class testReadFilesServices {
    private List<String> lines = new ArrayList<>();
    Path inDirectory = MonitoryFiles.createPath("in");
    File file = new File(inDirectory + File.separator + "arquivoTeste.dat");

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
    public void testConvertLineToObjectSalesman (){
        ReadFilesServices readFilesServices = new ReadFilesServices();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        Assert.assertEquals(Salesman.class,allDataInFile.get(0).getClass());
    }

    @Test
    public void testConvertLineToObjectClient (){
        ReadFilesServices readFilesServices = new ReadFilesServices();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        Assert.assertEquals(Salesman.class,allDataInFile.get(0).getClass());
    }

    @Test
    public void testConvertLineToObjectSale (){
        ReadFilesServices readFilesServices = new ReadFilesServices();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        Assert.assertEquals(Sale.class,allDataInFile.get(4).getClass());
    }


}

package service;

import main.MonitoryFiles;
import main.entity.Client;
import main.entity.Sale;
import main.entity.Salesman;
import main.service.ReadFilesServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
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
        lines.add("002ç2345675434544345çJose da SilvaçRural");
        lines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");

        try {
            file.createNewFile();
            BufferedWriter writer = Files.newBufferedWriter(inDirectory.resolve(file.toString()),ISO_8859_1);
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i));
                writer.newLine();
            }

            writer.close();
        } catch (IOException ex) {

        }
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
        Assert.assertEquals(Client.class,allDataInFile.get(1).getClass());
    }
//
    @Test
    public void testConvertLineToObjectSale (){
        ReadFilesServices readFilesServices = new ReadFilesServices();
        List<Object> allDataInFile = readFilesServices.mountObjects(inDirectory,file.toString());
        Assert.assertEquals(Sale.class,allDataInFile.get(2).getClass());
    }

    @Test
    public void testReadEachLineAndConvertToObject() {
        ReadFilesServices readFilesServices = new ReadFilesServices();
        List<String> allDataInFile = readFilesServices.readEachFile(inDirectory,file.toString());
    }
}

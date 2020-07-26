import main.entity.Client;
import main.entity.Sale;
import main.entity.Salesman;
import main.service.ReadFilesServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testReadFilesServices {
    private List<String> lines = new ArrayList<>();

    @Before
    public void setup(){
        lines.add("001ç1234567891234çPedroç50000");
        lines.add("002ç2345675434544345çJose da SilvaçRural");
        lines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
    }

    @Test
    public void testConvertLineToObjectSalesman (){
        ReadFilesServices readFilesServices = new ReadFilesServices();
        List<Object> allDataInFile = readFilesServices.mountObjects(lines);
        Assert.assertEquals(Salesman.class,allDataInFile.get(0).getClass());
    }

    @Test
    public void testConvertLineToObjectClient (){
        ReadFilesServices readFilesServices = new ReadFilesServices();
        List<Object> allDataInFile = readFilesServices.mountObjects(lines);
        Assert.assertEquals(Client.class,allDataInFile.get(1).getClass());
    }

    @Test
    public void testConvertLineToObjectSale (){
        ReadFilesServices readFilesServices = new ReadFilesServices();
        List<Object> allDataInFile = readFilesServices.mountObjects(lines);
        Assert.assertEquals(Sale.class,allDataInFile.get(2).getClass());
    }
}

package entity;

import main.entity.Client;
import org.junit.Assert;
import org.junit.Test;

public class testClient {
    public String name = "Eduardo";
    public String cnpj = "012345678912";
    public String area = "Rural";
    public Integer groupId = 001;

    @Test
    public void testClientGetName() {
        Client client = new Client(groupId,name,cnpj,area);
        Assert.assertEquals(client.getName(),name);
    }

    @Test
    public void testClientGetCnpj() {
        Client client = new Client(groupId,name,cnpj,area);
        Assert.assertEquals(client.getCnpj(),cnpj);
    }

    @Test
    public void testClientGetArea() {
        Client client = new Client(groupId,name,cnpj,area);
        Assert.assertEquals(client.getArea(),area);
    }
}

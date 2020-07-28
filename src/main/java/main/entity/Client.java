package main.entity;

public class Client {

    private String name;
    private String cnpj;
    private String area;

    public Client(Integer groupId, String name, String cnpj, String area) {
        this.name = name;
        this.cnpj = cnpj;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getArea() {
        return area;
    }

    public String toString() {
        return "Client(name=" + this.getName() + ", cnpj=" + this.getCnpj() + ", area=" + this.getArea() + ")";
    }
}

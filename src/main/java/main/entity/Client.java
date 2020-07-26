package main.entity;

public class Client {

    private Integer groupId;
    private String name;
    private String cnpj;
    private String area;

    public Client(Integer groupId, String name, String cnpj, String area) {
        this.groupId = groupId;
        this.name = name;
        this.cnpj = cnpj;
        this.area = area;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String toString() {
        return "Client(name=" + this.getName() + ", cnpj=" + this.getCnpj() + ", area=" + this.getArea() + ")";
    }
}

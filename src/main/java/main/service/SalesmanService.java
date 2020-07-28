package main.service;
import main.entity.Client;
import main.entity.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalesmanService {

    public Logger logger = LoggerFactory.getLogger(ClientService.class);
    public List<Salesman> salesmen = new ArrayList<>();
    public Salesman salesman = new Salesman();

    public Salesman getSalesmanByName(String parte, List<Salesman> salesmen) {
        for (int i = 0; i < salesmen.size(); i++) {
            if (salesmen.get(i).getName().equals(parte)) {
                this.salesman = salesmen.get(i);
            }
        }
        return this.salesman;
    }

    public Boolean salesmanExists(String cpf, List<Salesman> salesmen) {
        for (int i = 0; i < this.salesmen.size(); i++) {
            if (salesmen.get(i).getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public Salesman createSalesman(String[] parte, List<Object> allDataInFile, int lineNumber) {
        this.salesmen = getAllSalesman(allDataInFile);
        if ( !salesmanExists(parte[1],salesmen)){
            return new Salesman(Integer.parseInt(parte[0]),parte[2],parte[1],new BigDecimal(parte[3]));
        }
        this.logger.info("Vendedor da linha " + lineNumber +" já está cadastrado");
        return null;
    }

    public List<Salesman> getAllSalesman(List<Object> allDataInFile) {
        this.salesmen = allDataInFile.stream().filter(salesman -> salesman instanceof Salesman).
                map(salesman -> (Salesman) salesman).collect(Collectors.toList());
        return this.salesmen;
    }
}

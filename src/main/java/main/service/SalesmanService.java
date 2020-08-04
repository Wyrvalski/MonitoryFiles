package main.service;
import main.entity.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalesmanService {

    public Logger logger = LoggerFactory.getLogger(SalesmanService.class);
    public List<Salesman> salesmen = new ArrayList<>();

    public Salesman getSalesmanByName(String parte, List<Salesman> salesmen) {
        for (int i = 0; i < salesmen.size(); i++) {
            if (salesmen.get(i).getName().equals(parte)) {
                return salesmen.get(i);
            }
        }
        return null;
    }

    public Boolean salesmanExists(String cpf,String name, List<Salesman> salesmen) {
        for (int i = 0; i < salesmen.size(); i++) {
            if (salesmen.get(i).getCpf().equals(cpf) || salesmen.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Salesman createSalesman(String[] parte, List<Object> allDataInFile, int lineNumber) {
        this.salesmen = getAllSalesman(allDataInFile);
        if ( !salesmanExists(parte[1],parte[2],salesmen) ){
            return new Salesman(parte[2],parte[1],new BigDecimal(parte[3]));
        }
        this.logger.warn("Vendedor da linha " + lineNumber +" já está cadastrado com esse nome ou cpf");
        return null;
    }

    public List<Salesman> getAllSalesman(List<Object> allDataInFile) {

        this.salesmen = allDataInFile.stream().filter(salesman -> salesman instanceof Salesman).
                map(salesman -> (Salesman) salesman).collect(Collectors.toList());
        return this.salesmen;
    }
}

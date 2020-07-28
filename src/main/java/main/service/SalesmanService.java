package main.service;

import main.entity.Client;
import main.entity.Salesman;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalesmanService {

    List<Salesman> salesmen = new ArrayList<>();

    public Salesman getSalesmanByName(String parte, List<Salesman> salesmen) {
        Salesman salesman = new Salesman();
        for (int i = 0; i < salesmen.size(); i++) {
            if (salesmen.get(i).getName().equals(parte)) {
                salesman = salesmen.get(i);
            }
        }
        return salesman;
    }

    public List<Salesman> getAllSalesman(List<Object> allDataInFile) {
        this.salesmen = allDataInFile.stream().filter(salesman -> salesman instanceof Salesman).
                map(salesman -> (Salesman) salesman).collect(Collectors.toList());

        return this.salesmen;
    }
}
